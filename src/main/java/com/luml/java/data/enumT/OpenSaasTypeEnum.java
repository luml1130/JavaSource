package com.luml.java.data.enumT;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

/**
 * Saas 开通类型
 * @author luml
 */
@Getter
public enum OpenSaasTypeEnum {

    OPEN(1, "开通"),
    PLACE_ORDER(2, "下单");

    private int value;

    private String desc;

    OpenSaasTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean checkParam(Integer value) {
        if (value == null) {
            return false;
        }
        for (OpenSaasTypeEnum openSaasTypeEnum : OpenSaasTypeEnum.values()) {
            if (Objects.equals(openSaasTypeEnum.getValue(), value)) {
                return true;
            }
        }
        return false;
    }


    public static String getDescByValue(int value) {
        for (OpenSaasTypeEnum openSaasTypeEnum : OpenSaasTypeEnum.values()) {
            if (value == openSaasTypeEnum.value) {
                return openSaasTypeEnum.getDesc();
            }
        }
        return "";
    }

    public static int getValueByDesc(String desc){
        for (OpenSaasTypeEnum openSaasTypeEnum : OpenSaasTypeEnum.values()) {
            if(openSaasTypeEnum.getDesc().equals(desc)){
                return openSaasTypeEnum.getValue();
            }
        }
        return -1;
    }


    public static List<Integer> getValueList(){
        List<Integer> typeList = Lists.newArrayList();
        for (OpenSaasTypeEnum openSaasTypeEnum: OpenSaasTypeEnum.values()) {
                typeList.add(openSaasTypeEnum.value);
        }
        return typeList;
    }
}
