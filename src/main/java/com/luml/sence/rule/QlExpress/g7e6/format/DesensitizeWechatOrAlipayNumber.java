package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

public class DesensitizeWechatOrAlipayNumber extends DesensitizeOperator{

    public static final String OPERATOR_NAME = "脱敏微信或支付宝";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.ALIPAY_OR_WECHAT_NUMBER);
    }
}
