package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

public class DesensitizeCertificateValidityOrExpirationDate extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏证件有效期和失效期";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.CERTIFICATE_VALIDITY_OR_EXPIRATION_DATE);
    }
}
