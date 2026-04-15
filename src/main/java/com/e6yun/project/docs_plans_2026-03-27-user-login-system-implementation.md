# 用户登录系统实施计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**目标：** 为 e6-ms-tms-base 服务实现独立的用户登录认证系统，支持账号密码登录、Redis Token、登录失败锁定和密码过期策略

**架构：** 采用 Controller → Service → Gateway 三层架构，使用 Redis 存储 Token 和登录状态，BCrypt 加密密码，通过拦截器实现 Token 验证

**技术栈：** Spring Boot, MyBatis Plus, Redis, BCrypt, Lombok, MapStruct

---

## Task 1: 创建认证相关的 VO 对象

**文件：**
- Create: `e6-ms-tms-base-pojo/src/main/java/com/e6yun/project/tms/base/vo/auth/LoginCmd.java`
- Create: `e6-ms-tms-base-pojo/src/main/java/com/e6yun/project/tms/base/vo/auth/TokenVO.java`
- Create: `e6-ms-tms-base-pojo/src/main/java/com/e6yun/project/tms/base/vo/auth/ChangePasswordCmd.java`

**Step 1: 创建 LoginCmd.java**

```java
package com.e6yun.project.tms.base.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录请求")
public class LoginCmd {

    @ApiModelProperty(value = "用户账号", required = true)
    @NotBlank(message = "用户账号不能为空")
    private String userCode;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;
}
```

**Step 2: 创建 TokenVO.java**

```java
package com.e6yun.project.tms.base.vo.auth;

import com.e6yun.project.tms.base.vo.user.CurrentUserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Token响应")
public class TokenVO {

    @ApiModelProperty("访问令牌")
    private String token;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireAt;

    @ApiModelProperty("密码是否过期")
    private Boolean pwdExpired;

    @ApiModelProperty("提示信息")
    private String message;

    @ApiModelProperty("用户信息")
    private CurrentUserVO userInfo;
}
```

**Step 3: 创建 ChangePasswordCmd.java**

```java
package com.e6yun.project.tms.base.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel("修改密码请求")
public class ChangePasswordCmd {

    @ApiModelProperty(value = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "密码至少8位，包含大小写字母和数字")
    private String newPassword;
}
```

**Step 4: 提交**

```bash
cd e6-ms-tms-base-pojo
git add src/main/java/com/e6yun/project/tms/base/vo/auth/
git commit -m "feat(auth): 添加认证相关VO对象

- LoginCmd: 登录请求对象
- TokenVO: Token响应对象
- ChangePasswordCmd: 修改密码请求对象

Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>"
```

---

## Task 2: 创建认证服务接口和实现

**文件：**
- Create: `e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/service/auth/AuthService.java`
- Create: `e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/service/auth/impl/AuthServiceImpl.java`

**Step 1: 创建 AuthService.java 接口**

```java
package com.e6yun.project.tms.base.service.auth;

import com.e6yun.project.common.vo.E6Wrapper;
import com.e6yun.project.tms.base.vo.auth.ChangePasswordCmd;
import com.e6yun.project.tms.base.vo.auth.LoginCmd;
import com.e6yun.project.tms.base.vo.auth.TokenVO;

public interface AuthService {

    /**
     * 用户登录
     */
    E6Wrapper<TokenVO> login(LoginCmd cmd);

    /**
     * 用户登出
     */
    E6Wrapper<Void> logout(String token);

    /**
     * 刷新Token
     */
    E6Wrapper<TokenVO> refreshToken(String oldToken);

    /**
     * 修改密码
     */
    E6Wrapper<Void> changePassword(ChangePasswordCmd cmd);

    /**
     * 验证Token并获取用户信息
     */
    CurrentUserVO validateToken(String token);
}
```

**Step 2: 创建 AuthServiceImpl.java 实现（第1部分）**

```java
package com.e6yun.project.tms.base.service.auth.impl;

import com.e6yun.project.common.vo.E6Wrapper;
import com.e6yun.project.common.vo.E6WrapperUtil;
import com.e6yun.project.tms.base.service.auth.AuthService;
import com.e6yun.project.tms.base.service.user.gateway.impl.BaseE6UserGatewayImpl;
import com.e6yun.project.tms.base.model.po.user.BaseE6UserPO;
import com.e6yun.project.tms.base.vo.auth.ChangePasswordCmd;
import com.e6yun.project.tms.base.vo.auth.LoginCmd;
import com.e6yun.project.tms.base.vo.auth.TokenVO;
import com.e6yun.project.tms.base.vo.user.CurrentUserVO;
import com.e6yun.project.tms.exception.E6ArgumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BaseE6UserGatewayImpl userGateway;
    private final StringRedisTemplate redisTemplate;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 配置常量
    private static final int MAX_FAIL_COUNT = 5;
    private static final int LOCK_DURATION_SECONDS = 1800; // 30分钟
    private static final int FAIL_COUNT_EXPIRE_SECONDS = 900; // 15分钟
    private static final int TOKEN_EXPIRE_SECONDS = 7200; // 2小时
    private static final int PASSWORD_EXPIRE_DAYS = 90;

    // Redis Key 前缀
    private static final String TOKEN_KEY_PREFIX = "auth:token:";
    private static final String FAIL_KEY_PREFIX = "auth:fail:";
    private static final String LOCK_KEY_PREFIX = "auth:lock:";

    @Override
    public E6Wrapper<TokenVO> login(LoginCmd cmd) {
        String userCode = cmd.getUserCode();

        // 1. 检查账号是否被锁定
        checkLoginLock(userCode);

        // 2. 查询用户
        BaseE6UserPO user = userGateway.getByUserCode(userCode);
        if (user == null) {
            incrementFailCount(userCode);
            throw new E6ArgumentException("用户名或密码错误");
        }

        // 3. 验证密码
        if (!passwordEncoder.matches(cmd.getPassword(), user.getPassword())) {
            incrementFailCount(userCode);
            throw new E6ArgumentException("用户名或密码错误");
        }

        // 4. 清除失败计数
        clearFailCount(userCode);

        // 5. 检查密码是否过期
        boolean pwdExpired = checkPasswordExpired(user);

        // 6. 生成Token
        String token = generateToken();
        CurrentUserVO userInfo = buildCurrentUserVO(user);

        // 7. 存储Token到Redis
        storeToken(token, userInfo);

        // 8. 构建响应
        TokenVO tokenVO = TokenVO.builder()
                .token(token)
                .expireAt(LocalDateTime.now().plusSeconds(TOKEN_EXPIRE_SECONDS))
                .pwdExpired(pwdExpired)
                .message(pwdExpired ? "密码已过期，请修改密码" : "登录成功")
                .userInfo(userInfo)
                .build();

        return E6WrapperUtil.wrap(tokenVO);
    }

    // 后续方法将在下一步添加...
}
```

**Step 3: 继续完成 AuthServiceImpl.java（第2部分）**

在 AuthServiceImpl.java 中添加以下私有方法：

```java
    /**
     * 检查账号是否被锁定
     */
    private void checkLoginLock(String userCode) {
        String lockKey = LOCK_KEY_PREFIX + userCode;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(lockKey))) {
            throw new E6ArgumentException("账号已锁定，请30分钟后重试");
        }
    }

    /**
     * 增加失败计数
     */
    private void incrementFailCount(String userCode) {
        String failKey = FAIL_KEY_PREFIX + userCode;
        Long failCount = redisTemplate.opsForValue().increment(failKey);

        if (failCount == 1) {
            // 首次失败，设置过期时间
            redisTemplate.expire(failKey, FAIL_COUNT_EXPIRE_SECONDS, TimeUnit.SECONDS);
        }

        if (failCount >= MAX_FAIL_COUNT) {
            // 达到最大失败次数，锁定账号
            String lockKey = LOCK_KEY_PREFIX + userCode;
            redisTemplate.opsForValue().set(lockKey, String.valueOf(System.currentTimeMillis()),
                    LOCK_DURATION_SECONDS, TimeUnit.SECONDS);
        }
    }

    /**
     * 清除失败计数
     */
    private void clearFailCount(String userCode) {
        String failKey = FAIL_KEY_PREFIX + userCode;
        redisTemplate.delete(failKey);
    }

    /**
     * 检查密码是否过期
     */
    private boolean checkPasswordExpired(BaseE6UserPO user) {
        if (user.getLastPasswordModifiedTime() == null) {
            return false;
        }

        long days = ChronoUnit.DAYS.between(
                user.getLastPasswordModifiedTime().toInstant(),
                LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)
        );

        return days > PASSWORD_EXPIRE_DAYS;
    }

    /**
     * 生成Token
     */
    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 构建用户信息VO
     */
    private CurrentUserVO buildCurrentUserVO(BaseE6UserPO user) {
        // 使用 MapStruct 或手动映射
        CurrentUserVO vo = new CurrentUserVO();
        vo.setUserId(user.getUserId());
        vo.setUserCode(user.getUserCode());
        vo.setUserName(user.getUserName());
        vo.setCorpId(user.getCorpId());
        vo.setOrgId(user.getOrgId());
        // ... 其他字段映射
        return vo;
    }

    /**
     * 存储Token到Redis
     */
    private void storeToken(String token, CurrentUserVO userInfo) {
        String tokenKey = TOKEN_KEY_PREFIX + token;
        // 将用户信息序列化为JSON存储
        String userInfoJson = JSON.toJSONString(userInfo);
        redisTemplate.opsForValue().set(tokenKey, userInfoJson,
                TOKEN_EXPIRE_SECONDS, TimeUnit.SECONDS);
    }

    @Override
    public E6Wrapper<Void> logout(String token) {
        String tokenKey = TOKEN_KEY_PREFIX + token;
        redisTemplate.delete(tokenKey);
        return E6WrapperUtil.wrap(null);
    }

    @Override
    public E6Wrapper<TokenVO> refreshToken(String oldToken) {
        // 验证旧Token
        CurrentUserVO userInfo = validateToken(oldToken);
        if (userInfo == null) {
            throw new E6ArgumentException("Token无效");
        }

        // 删除旧Token
        redisTemplate.delete(TOKEN_KEY_PREFIX + oldToken);

        // 生成新Token
        String newToken = generateToken();
        storeToken(newToken, userInfo);

        TokenVO tokenVO = TokenVO.builder()
                .token(newToken)
                .expireAt(LocalDateTime.now().plusSeconds(TOKEN_EXPIRE_SECONDS))
                .build();

        return E6WrapperUtil.wrap(tokenVO);
    }

    @Override
    public CurrentUserVO validateToken(String token) {
        String tokenKey = TOKEN_KEY_PREFIX + token;
        String userInfoJson = redisTemplate.opsForValue().get(tokenKey);

        if (userInfoJson == null) {
            return null;
        }

        return JSON.parseObject(userInfoJson, CurrentUserVO.class);
    }

    @Override
    public E6Wrapper<Void> changePassword(ChangePasswordCmd cmd) {
        // 从 CurrentUserHolder 获取当前用户
        CurrentUserVO currentUser = CurrentUserHolder.getCurrentUser();
        BaseE6UserPO user = userGateway.getById(currentUser.getUserId());

        // 验证旧密码
        if (!passwordEncoder.matches(cmd.getOldPassword(), user.getPassword())) {
            throw new E6ArgumentException("旧密码错误");
        }

        // 更新密码
        user.setPassword(passwordEncoder.encode(cmd.getNewPassword()));
        user.setLastPasswordModifiedTime(new Date());
        user.setPwdExpired(false);
        userGateway.updateById(user);

        return E6WrapperUtil.wrap(null);
    }
}
```

**Step 4: 提交**

```bash
git add e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/service/auth/
git commit -m "feat(auth): 实现认证服务

- 登录验证（BCrypt密码校验）
- 登录失败锁定机制（5次失败锁定30分钟）
- 密码过期检查（90天）
- Token生成和存储（Redis，2小时有效期）
- 登出、Token刷新、修改密码功能

Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>"
```

---

## Task 3: 创建认证控制器

**文件：**
- Create: `e6-ms-tms-base-web/src/main/java/com/e6yun/project/tms/base/controller/auth/AuthController.java`

**Step 1: 创建 AuthController.java**

```java
package com.e6yun.project.tms.base.controller.auth;

import com.e6yun.project.common.vo.E6Wrapper;
import com.e6yun.project.tms.base.service.auth.AuthService;
import com.e6yun.project.tms.base.vo.auth.ChangePasswordCmd;
import com.e6yun.project.tms.base.vo.auth.LoginCmd;
import com.e6yun.project.tms.base.vo.auth.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = {"认证管理接口"})
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "账号密码登录")
    public E6Wrapper<TokenVO> login(@Validated @RequestBody LoginCmd cmd) {
        return authService.login(cmd);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出", notes = "清除Token")
    public E6Wrapper<Void> logout(HttpServletRequest request) {
        String token = extractToken(request);
        return authService.logout(token);
    }

    @PostMapping("/refresh")
    @ApiOperation(value = "刷新Token", notes = "使用旧Token换取新Token")
    public E6Wrapper<TokenVO> refreshToken(HttpServletRequest request) {
        String token = extractToken(request);
        return authService.refreshToken(token);
    }

    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码", notes = "修改当前用户密码")
    public E6Wrapper<Void> changePassword(@Validated @RequestBody ChangePasswordCmd cmd) {
        return authService.changePassword(cmd);
    }

    /**
     * 从请求头中提取Token
     */
    private String extractToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }
}
```

**Step 2: 提交**

```bash
git add e6-ms-tms-base-web/src/main/java/com/e6yun/project/tms/base/controller/auth/
git commit -m "feat(auth): 添加认证控制器

提供以下REST接口：
- POST /auth/login - 用户登录
- POST /auth/logout - 用户登出
- POST /auth/refresh - 刷新Token
- POST /auth/changePassword - 修改密码

Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>"
```

---

## Task 4: 创建Token验证拦截器

**文件：**
- Create: `e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/config/AuthInterceptor.java`
- Modify: `e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/config/WebMvcConfig.java`

**Step 1: 创建 AuthInterceptor.java**

```java
package com.e6yun.project.tms.base.config;

import com.e6yun.project.tms.base.service.auth.AuthService;
import com.e6yun.project.tms.base.vo.user.CurrentUserVO;
import com.e6yun.project.tms.common.framework.context.user.CurrentUserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头获取Token
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authorization.substring(7);

        // 验证Token
        CurrentUserVO userInfo = authService.validateToken(token);
        if (userInfo == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 设置当前用户到上下文
        CurrentUserHolder.setCurrentUser(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // 清除当前用户上下文
        CurrentUserHolder.clear();
    }
}
```

**Step 2: 修改 WebMvcConfig.java 注册拦截器**

找到 WebMvcConfig 类，添加拦截器注册：

```java
@Autowired
private AuthInterceptor authInterceptor;

@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                    "/auth/login",      // 登录接口不拦截
                    "/swagger-ui.html", // Swagger文档不拦截
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/v2/api-docs"
            );
}
```

**Step 3: 提交**

```bash
git add e6-ms-tms-base-service/src/main/java/com/e6yun/project/tms/base/config/
git commit -m "feat(auth): 添加Token验证拦截器

- 拦截所有请求验证Token
- 从Redis获取用户信息并设置到CurrentUserHolder
- 排除登录接口和Swagger文档

Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>"
```

---

## Task 5: 添加配置和依赖

**文件：**
- Modify: `e6-ms-tms-base-web/src/main/resources/application.yml`
- Modify: `e6-ms-tms-base-service/pom.xml`

**Step 1: 在 application.yml 添加认证配置**

```yaml
# 认证配置
auth:
  login:
    fail-max-count: 5           # 最大失败次数
    lock-duration: 1800         # 锁定时长(秒)
    fail-count-expire: 900      # 失败计数过期时间(秒)
  token:
    expire-seconds: 7200        # Token有效期(秒)
  password:
    expire-days: 90             # 密码过期天数
```

**Step 2: 在 pom.xml 添加 BCrypt 依赖（如果没有）**

```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-crypto</artifactId>
</dependency>
```

**Step 3: 提交**

```bash
git add e6-ms-tms-base-web/src/main/resources/application.yml
git add e6-ms-tms-base-service/pom.xml
git commit -m "feat(auth): 添加认证配置和依赖

- 添加登录失败锁定配置
- 添加Token和密码过期配置
- 添加Spring Security Crypto依赖（BCrypt）

Co-Authored-By: Claude Opus 4.6 <noreply@anthropic.com>"
```

---

## Task 6: 测试验证

**Step 1: 启动应用**

```bash
cd e6-ms-tms-base-web
mvn spring-boot:run
```

**Step 2: 测试登录接口**

使用 curl 或 Postman 测试：

```bash
# 登录
curl -X POST http://localhost:30020/auth/login \
  -H "Content-Type: application/json" \
  -d '{"userCode":"admin","password":"123456"}'

# 预期响应：
# {
#   "code": 200,
#   "data": {
#     "token": "xxx",
#     "expireAt": "2026-03-27T18:00:00",
#     "pwdExpired": false,
#     "userInfo": {...}
#   }
# }
```

**Step 3: 测试Token验证**

```bash
# 使用Token访问其他接口
curl -X GET http://localhost:30020/user/getCurrentUser \
  -H "Authorization: Bearer {token}"
```

**Step 4: 测试登录失败锁定**

```bash
# 连续5次错误密码登录
for i in {1..5}; do
  curl -X POST http://localhost:30020/auth/login \
    -H "Content-Type: application/json" \
    -d '{"userCode":"admin","password":"wrong"}'
done

# 第6次应返回账号锁定错误
```

**Step 5: 测试密码修改**

```bash
curl -X POST http://localhost:30020/auth/changePassword \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{"oldPassword":"123456","newPassword":"NewPass123"}'
```

---

## 验收标准

- [ ] 登录接口正常工作，返回Token
- [ ] Token验证拦截器正常工作
- [ ] 登录失败5次后账号被锁定30分钟
- [ ] 密码过期检查正常工作
- [ ] 修改密码功能正常
- [ ] Token刷新功能正常
- [ ] 登出功能正常清除Token
- [ ] Swagger文档可正常访问
- [ ] 所有代码已提交到git

---

## 后续优化建议

1. **单元测试：** 为 AuthService 编写单元测试
2. **集成测试：** 编写API集成测试
3. **日志审计：** 记录登录日志（IP、设备、时间）
4. **配置外部化：** 将配置移到Nacos配置中心
5. **图形验证码：** 添加验证码防止暴力破解
6. **多端登录控制：** 支持单点登录或多端同时在线