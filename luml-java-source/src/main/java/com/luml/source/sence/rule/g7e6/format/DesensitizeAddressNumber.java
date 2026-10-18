package com.luml.source.sence.rule.g7e6.format;

/*
import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;
*/

public class DesensitizeAddressNumber extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏地址";

    @Override
    public String desensitize(String data) {
        // return MaskingTool.mask(data, MaskingType.ADDRESS);
       // return DesensitizedUtil.address(data);
        return null;
    }
}
