package com.luml.java.data.json.fastjson.Serializer.e6yun;



/**
 * 车辆来源枚举类
 * @author houjun@e6yun.com
 * @date 2021/12/5 14:08
 */
public enum WaybillVehicleSourceEnum implements TmsBaseEnum {
    /**
     * 默认
     */
    DEFAULT(0,""),

    /**
     * 好多车
     */
    HDC(1, "好多车"),

    /**
     * 运满满
     */
    YMM(2, "运满满"),
    ;

    private final Integer code;

    private final String label;

    WaybillVehicleSourceEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static String getLabelByCode(Integer code) {
        for (WaybillVehicleSourceEnum value : values()) {
            if (code.equals(value.getCode())) {
                return value.label;
            }
        }
        return null;
    }

    @Override
    public int getId() {
        return code;
    }
}
