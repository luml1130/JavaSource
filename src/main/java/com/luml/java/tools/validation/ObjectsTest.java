package com.luml.java.tools.validation;

import com.alibaba.fastjson.JSON;
import com.luml.domain.City;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;
import java.util.Set;

/**
 * @author luml
 * @description
 * @date 2025/11/17
 */
public class ObjectsTest {

    public static void main(String[] args) {
        City city = new City();
        city.setCity("西安");
        System.out.println(Objects.isNull(city.getProvince()));
    }

    /**
     * 对象校验
     * 1、需配合 @Valid 或 @Validated 注解触发校验，
     * 2、使用如下方法
     */
    @Test
    public void testObject(){
        User user = new User();
        user.setAge(19);
       // user.setName("");
        String jsonS = JSON.toJSONString(user);
        User user1 = JSON.parseObject(jsonS,User.class);

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> validate = validator.validate(user1);

        if (CollectionUtils.isNotEmpty(validate)) {
            ConstraintViolation<User> userConstraintViolation = validate.stream().findFirst().orElse(null);
            if (!Objects.isNull(userConstraintViolation)) {
                System.out.println(userConstraintViolation.getMessage());
               // throw new E6ArgumentException(I18nDictUtil.getI18nValue(carrierCmdConstraintViolation.getMessage()));
            }
        }
    }
}
