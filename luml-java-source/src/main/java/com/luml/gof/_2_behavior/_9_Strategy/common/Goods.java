package com.luml.gof._2_behavior._9_Strategy.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luml
 * @description
 * @date 2020/12/8
 */
@Getter
@Setter
@AllArgsConstructor
public class Goods {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Float price;
}
