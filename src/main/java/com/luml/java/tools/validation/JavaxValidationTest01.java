package com.luml.java.tools.validation;

import com.google.common.collect.Lists;
import com.luml.java.tools.validation.domain.UserVal;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2025/11/1
 */
public class JavaxValidationTest01 {

    @Test
    public void objectTest(){
        // 创建一个符合要求的 hobbies 列表
        // List.of();
        List<String> validHobbies = Lists.newArrayList("reading", "swimming", "coding");

        // 创建 User 对象
        UserVal userVal = new UserVal(validHobbies);

        // 获取 Validator 实例
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // 验证对象
        Set<ConstraintViolation<UserVal>> violations = validator.validate(userVal);

        // 输出验证结果
        if (violations.isEmpty()) {
            System.out.println("Validation passed.");
        } else {
            for (ConstraintViolation<UserVal> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
    }
}
