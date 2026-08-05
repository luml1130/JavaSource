package com.luml.utiltools.lombok;

import lombok.Data;

/**
 * @Data (最常用)
 *
 * @Data 是一个组合注解，相当于同时使用了
 * @Getter、@Setter、@ToString、@EqualsAndHashCode 和 @RequiredArgsConstructor。
 * 它适用于大多数实体类（POJO）。
 *
 * 效果‌：编译后，该类将自动拥有
 * 所有字段的 getter/setter 方法、toString()、equals()、hashCode()
 * 以及一个包含所有非 final 字段的构造函数。
 */
@Data
public class User {
    private Long id;
    private String name;
    private String email;
}
