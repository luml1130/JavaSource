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
 * 脱敏其他常用司机
 * 数据举例：安好 13212121212；小柒 17000000000
 * 业务场景：车辆档案 其他常用司机
 */
public class DesensitizeOtherCommonDrivers extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏其他常用司机";
    
    /**
     * 这是个中文分号！！！
     */
    private static final String SEMICOLON = "；";

	@Override
	public String desensitize(String data) {
		String[] items = StringUtils.split(data, SEMICOLON);
        List<String> desensitizedList = Arrays.stream(items).map(deliveryman -> {
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
