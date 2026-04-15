# 用户登录系统设计方案

**日期：** 2026-03-27
**项目：** e6-ms-tms-base (易流云3.0基础数据服务)
**设计目标：** 为本服务设计独立的用户登录认证系统

---

## 1. 概述

本设计方案为 e6-ms-tms-base 服务提供独立的用户登录认证功能，支持账号密码登录方式，采用 Redis Token 方案，并包含登录失败锁定和密码过期策略等安全机制。

### 核心需求
- **认证方式：** 账号密码登录
- **Token 方案：** Redis Token（支持分布式部署）
- **用户范围：** 统一登录入口，支持所有用户类型
- **安全增强：** 登录失败锁定、密码过期策略

---

## 2. 架构设计

### 2.1 登录流程

```
客户端
  ↓ POST /auth/login {userCode, password}
AuthController
  ↓
AuthService
  ├─→ UserGateway.getByUserCode()          # 查询用户
  ├─→ checkLoginLock(userCode)             # Redis检查锁定状态
  ├─→ BCrypt.matches(password, dbPassword) # 验证密码
  ├─→ checkPasswordExpired()               # 检查密码过期
  ├─→ generateToken()                      # 生成UUID Token
  ├─→ Redis.set("auth:token:{token}")      # 存储Token→用户信息
  └─→ clearLoginFailCount()                # 清除失败计数
  ↓
返回 {token, expireAt, pwdExpired, userInfo}
```

### 2.2 Token验证流程

```
客户端请求 Header: Authorization: Bearer {token}
  ↓
AuthInterceptor
  ├─→ Redis.get("auth:token:{token}")
  ├─→ 解析用户信息 → CurrentUserHolder.set()
  └─→ 放行请求
```

---

## 3. 数据模型设计

### 3.1 Redis 数据结构

```
# Token存储 (有效期2小时)
Key: auth:token:{uuid}
Value: JSON {userId, userCode, userName, corpId, orgId, roleIdList, ...}
TTL: 7200秒

# 登录失败计数 (有效期15分钟)
Key: auth:fail:{userCode}
Value: 失败次数
TTL: 900秒

# 账号锁定标记 (有效期30分钟)
Key: auth:lock:{userCode}
Value: 锁定时间戳
TTL: 1800秒
```

### 3.2 数据库表（复用现有）

**base_e6_user 表已有字段：**
- `user_code` - 用户名
- `password` - 密码（需BCrypt加密存储）
- `last_password_modified_time` - 上次密码修改时间
- `pwd_expired` - 密码是否过期标记

### 3.3 系统配置

```properties
# 可通过 Nacos 配置或配置文件
login.fail.max.count=5           # 最大失败次数
login.lock.duration=1800         # 锁定时长(秒)
password.expire.days=90          # 密码过期天数
token.expire.seconds=7200        # Token有效期
```

---

## 4. 核心接口设计

### 4.1 登录接口

**请求：**
```
POST /auth/login
Content-Type: application/json

{
  "userCode": "admin",
  "password": "123456"
}
```

**成功响应：**
```json
{
  "code": 200,
  "data": {
    "token": "uuid-token-string",
    "expireAt": "2026-03-27T18:00:00",
    "pwdExpired": false,
    "userInfo": {
      "userId": 1,
      "userName": "管理员",
      "corpId": 100,
      "orgId": 10,
      "roleIdList": [1, 2]
    }
  }
}
```

**错误响应：**
- `401` - 用户名或密码错误
- `423` - 账号已锁定，请30分钟后重试
- `428` - 密码已过期，请修改密码

### 4.2 登出接口

```
POST /auth/logout
Header: Authorization: Bearer {token}

Response: {"code": 200, "message": "登出成功"}
```

### 4.3 Token刷新接口

```
POST /auth/refresh
Header: Authorization: Bearer {token}

Response: {
  "token": "new-uuid-token",
  "expireAt": "2026-03-27T20:00:00"
}
```

### 4.4 修改密码接口

```
POST /auth/changePassword
Header: Authorization: Bearer {token}

Request: {
  "oldPassword": "123456",
  "newPassword": "newPass123"
}
```

---

## 5. 安全机制设计

### 5.1 登录失败锁定

```java
// 伪代码逻辑
String failKey = "auth:fail:" + userCode;
Integer failCount = redis.get(failKey);

if (failCount >= 5) {
    String lockKey = "auth:lock:" + userCode;
    if (redis.exists(lockKey)) {
        throw new AccountLockedException("账号已锁定30分钟");
    }
}

// 密码验证失败
if (!BCrypt.matches(password, dbPassword)) {
    redis.incr(failKey);
    redis.expire(failKey, 900); // 15分钟过期

    if (redis.get(failKey) >= 5) {
        redis.set(lockKey, System.currentTimeMillis(), 1800);
    }
    throw new AuthenticationException("用户名或密码错误");
}

// 登录成功，清除失败计数
redis.del(failKey);
```

### 5.2 密码过期检查

```java
// 检查密码是否过期（90天策略）
Date lastModified = user.getLastPasswordModifiedTime();
if (lastModified != null) {
    long days = ChronoUnit.DAYS.between(
        lastModified.toInstant(),
        Instant.now()
    );

    if (days > 90) {
        return TokenVO.builder()
            .pwdExpired(true)
            .message("密码已过期，请修改密码")
            .build();
    }
}
```

---

## 6. 实现文件清单

### 6.1 新增文件

```
e6-ms-tms-base-pojo/
  └── vo/auth/
      ├── LoginCmd.java           # 登录请求
      ├── TokenVO.java            # Token响应
      └── ChangePasswordCmd.java  # 修改密码请求

e6-ms-tms-base-service/
  ├── service/auth/
  │   ├── AuthService.java        # 认证服务接口
  │   └── impl/
  │       └── AuthServiceImpl.java
  ├── gateway/auth/
  │   └── impl/
  │       └── AuthGatewayImpl.java
  └── config/
      └── AuthInterceptor.java    # Token拦截器

e6-ms-tms-base-web/
  └── controller/auth/
      └── AuthController.java     # 登录控制器
```

### 6.2 修改文件

```
e6-ms-tms-base-service/
  └── config/WebMvcConfig.java    # 注册拦截器

application.yml                    # 添加认证配置
```

---

## 7. 技术选型说明

### 7.1 为什么选择 Redis Token？

- **分布式支持：** 支持多实例部署，Token 在所有节点共享
- **性能优越：** Redis 内存存储，验证速度快
- **TTL 机制：** 自动过期，无需手动清理
- **已有基础设施：** 项目已集成 Redis

### 7.2 为什么选择 BCrypt？

- **安全性高：** 自动加盐，防止彩虹表攻击
- **计算成本可调：** 可配置工作因子
- **Spring Security 内置：** 无需额外依赖

---

## 8. 后续扩展建议

1. **多端登录控制：** 支持单点登录或多端同时在线
2. **登录日志审计：** 记录登录IP、设备、时间等
3. **图形验证码：** 防止暴力破解
4. **短信验证码登录：** 复用现有 sendCaptcha/checkCaptcha 接口
5. **OAuth2集成：** 支持第三方登录（微信、钉钉等）

---

## 9. 实施计划

详细的实施计划将通过 `writing-plans` 技能生成，包括：
- 分步实现任务
- 文件创建顺序
- 测试验证步骤
- 部署注意事项
