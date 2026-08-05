package com.luml.utiltools.lombok;

import lombok.Getter;
import lombok.Setter;

/**
 * @Getter / @Setter
 * 可以作用于类或单个字段，用于精细控制哪些字段需要生成访问器。
 */
public class Config {
    @Getter @Setter
    private String apiKey;

    @Getter // 只生成 getter，不生成 setter (只读)
    private final String version = "1.0";
}
