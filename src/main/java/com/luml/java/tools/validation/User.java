package com.luml.java.tools.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author luml
 * @description
 * @date 2025/11/1
 */
public class User {
    @NotNull(message = "Name cannot be null")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @Min(value = 18, message = "Age must be at least 18")
    private int age;
}
