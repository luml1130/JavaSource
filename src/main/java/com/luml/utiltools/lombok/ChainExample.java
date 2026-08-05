package com.luml.utiltools.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Accessors (链式调用)
 * 使 Setter 方法返回当前对象 (this)，支持链式调用。
 */
@Data
@Accessors(chain = true)
public class ChainExample {
    private String name;
    private int age;

    public static void main(String[] args) {
        ChainExample example = new ChainExample()
                .setName("Bob")
                .setAge(30);
        System.out.println(example);
    }
}
