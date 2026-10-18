package com.luml.source.sence.rule.Desensitized.Json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class UserDTO {
    @JsonSerialize(using = PhoneMaskSerializer.class)
    private String phoneNumber;

    // getters and setters
}
