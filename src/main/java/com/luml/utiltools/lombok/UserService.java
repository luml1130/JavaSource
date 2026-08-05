package com.luml.utiltools.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * @Slf4j (日志支持)
 *
 * 自动生成名为 log 的 SLF4J 日志对象，无需手动编写 LoggerFactory.getLogger(...)。
 */
@Slf4j
public class UserService {
    public void createUser(String name) {
        // 直接使用 log 对象
        log.info("Creating user: {}", name);
        try {
            // 业务逻辑
        } catch (Exception e) {
            log.error("Failed to create user", e);
        }
    }
}
