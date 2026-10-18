package com.luml.utiltools.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AllArgsConstructor & @NoArgsConstructor
 * 分别生成全参构造器和无参构造器。通常与 @Data 配合使用，
 * 因为 @Data 默认只生成 @RequiredArgsConstructor（仅包含 final 或 @NonNull 字段）。
 */
@Data
@NoArgsConstructor // 生成无参构造
@AllArgsConstructor // 生成全参构造
public class Product {
    private Long id;
    private String productName;
    private double price;
}
