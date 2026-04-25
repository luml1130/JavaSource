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
 * 脱敏送货员列表
 * 数据举例：熊大 17300000000;asdasd 15691763261
 * 业务场景：车辆档案 绑定送货员
 */
public class DesensitizeDeliverymanList extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏送货员列表";

    private static final String SEMICOLON = ";";

    @Override
    public String desensitize(String data) {
        String[] deliverymanList = StringUtils.split(data, SEMICOLON);
        List<String> desensitizedList = Arrays.stream(deliverymanList).map(deliveryman -> {
            final String[] NAME_PHONE = StringUtils.split(deliveryman, StrUtil.C_SPACE);
            if (NAME_PHONE.length == 2) {
                NAME_PHONE[0] = MaskingTool.mask(NAME_PHONE[0], MaskingType.NAME);
                NAME_PHONE[1] = MaskingTool.mask(NAME_PHONE[1], MaskingType.PHONE_NUMBER);
                return StringUtils.join(NAME_PHONE, StrUtil.C_SPACE);
            } else {
                return MaskingTool.mask(deliveryman, MaskingType.OTHERS);
            }
        }).collect(Collectors.toList());
        return StringUtils.join(desensitizedList, SEMICOLON);
    }
}
