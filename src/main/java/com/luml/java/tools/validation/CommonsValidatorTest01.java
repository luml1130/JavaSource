package com.luml.java.tools.validation;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * @author luml
 * @description
 * @date 2025/11/1
 */
public class CommonsValidatorTest01 {
    public static void main(String[] args) {
        String email = "example@example.com";
        boolean isValid = EmailValidator.getInstance().isValid(email);
        System.out.println("Is valid email: " + isValid);
    }
}

