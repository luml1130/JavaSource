package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

public class DesensitizeContractNumber extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏合同编号";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.CONTRACT_NUMBER);
    }
}
