package com.luml.source.sence.rule.g7e6.format;

/*import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;*/

public class DesensitizeVehicleEngineNumber extends DesensitizeOperator{

    public static final String OPERATOR_NAME = "脱敏车辆发动机号码";

    @Override
    public String desensitize(String data) {
        return "";
       // return MaskingTool.mask(data, MaskingType.VEHICLE_ENGINE_NUMBER);
    }
}
