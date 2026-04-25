package com.luml.sence.rule.QlExpress.g7e6.format;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;

public class DesensitizeVehicleEngineNumber extends DesensitizeOperator{

    public static final String OPERATOR_NAME = "脱敏车辆发动机号码";

    @Override
    public String desensitize(String data) {
        return MaskingTool.mask(data, MaskingType.VEHICLE_ENGINE_NUMBER);
    }
}
