package com.luml.java.tools.validation.domain;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/3/31
 */
public class UserVal {
    @Size(min = 1, max = 5, message = "Hobbies size must be between 1 and 5")
    private List<String> hobbies;

    // 构造函数
    public UserVal(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    // Getter 和 Setter
    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

}
