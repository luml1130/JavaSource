package com.luml.domain.enu;

/**
 * @author houjun@e6yun.com
 * @Description  运单唯一编码生成类型 枚举
 * @Date 2020年8月3日15点16分
 */
public enum UniqueCodeTypeEnum {

    /**
     * 订单
     */
    GENERAL_ORDER(1, "W"),

    /**
     * 运单
     */
    GENERAL_WAYBILL(2, "T"),

    /**
     * 津贴规则编码
     */
    GENERAL_ALLOWANCE(3,"A"),

    /**
     * 合同编码
     */
    GENERAL_CONTRACT(4,"C"),

    /**
     * 智能排线
     */
    GENERAL_SCHEDULE(5,"ZN"),

    /**
     * 园区预约单号
     */
    GENERAL_PARK(6, "P"),

    /**
     * 园区预约单号-APP
     */
    GENERAL_PARK_APP(7, "P-APP"),

    /**
     * OMS-发货单号
     */
    GENERAL_OMS_SHIP(10, "F"),

    /**
     * 生成班次单号
     */
    GENERAL_SHIFT(20, "ZN"),

    /**
     * 采购需求编码
     */
    GENERAL_PURCHASE(30,"PR")
    ;

    private Integer type;

    private String prefix;

    UniqueCodeTypeEnum(Integer type, String prefix) {
        this.type = type;
        this.prefix = prefix;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
