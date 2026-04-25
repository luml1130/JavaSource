package com.luml.sence.rule.QlExpress.g7e6.format.special;

import cn.hutool.core.util.StrUtil;

import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeOperator;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 脱敏车牌列表
 * 数据举例：陕U52779,陕K88888,陕G9090
 * 业务场景：送货员管理 绑定车辆
 */
public class DesensitizeVehicleNumberList extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏车牌号列表";

    @Override
    public String desensitize(String data) {

        List<String> desensitizedVehicleNumberList = Arrays.stream(StringUtils.split(data, StrUtil.COMMA))
                .map(vehicleNumber -> MaskingTool.mask(vehicleNumber, MaskingType.CAR_NUMBER))
                .collect(Collectors.toList());

        return StringUtils.join(desensitizedVehicleNumberList, StrUtil.COMMA);
    }
}
