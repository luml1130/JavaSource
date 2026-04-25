package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

public class DesensitizeOthers extends DesensitizeOperator{

    public static final String OPERATOR_NAME = "脱敏其他";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.OTHERS);
    }
}
