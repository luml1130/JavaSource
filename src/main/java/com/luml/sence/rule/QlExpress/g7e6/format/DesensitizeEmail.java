package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

/**
 * 脱敏电子邮件
 */
public class DesensitizeEmail extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏邮箱";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.EMAIL);
    }
}
