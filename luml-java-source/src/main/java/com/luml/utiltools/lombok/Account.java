package com.luml.utiltools.lombok;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "password") // 排除 password 字段
@Getter
@Setter
public class Account {
    private String username;
    private String password;

    public static void main(String[] args) {
        Account account = new Account();
        account.setPassword("ddd");
        account.setUsername("lu");
        System.out.println(account);
        //Account(username=lu)
    }
}
