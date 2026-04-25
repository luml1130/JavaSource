package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

/**
 * 姓名脱敏
 */
public class DesensitizePersonalName extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏姓名";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.NAME);
    }

}
