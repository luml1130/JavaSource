package com.luml.source.sence.rule.QlExpress.g7e6.format;

/*import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;*/

import cn.hutool.core.util.DesensitizedUtil;

public class DesensitizeContractNumber extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏合同编号";

    @Override
    public String desensitize(String data) {
        //return MaskingTool.mask(data, MaskingType.CONTRACT_NUMBER);

        /*DesensitizedUtil.DesensitizedType type = DesensitizedUtil.DesensitizedType.CAR_LICENSE;
        return DesensitizedUtil.desensitized(data, type);*/

        return null;
    }
}
