package com.luml.sence.rule.QlExpress.g7e6.format.special;

import cn.hutool.core.util.StrUtil;
import com.chinawayltd.ops.security.masking.MaskingTool;
import com.chinawayltd.ops.security.masking.MaskingType;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeOperator;
import org.apache.commons.lang3.StringUtils;


/**
 * 脱敏主司机
 * 数据举例：安好 13212121212 (主司机)
 * 业务场景：车辆档案 绑定主司机
 */
public class DesensitizeMainDriver extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏主司机";

    @Override
    public String desensitize(String data) {
        String[] items = StringUtils.split(data, StrUtil.C_SPACE);
        if (items.length == 3) {
            items[0] = MaskingTool.mask(items[0], MaskingType.NAME);
            items[1] = MaskingTool.mask(items[1], MaskingType.PHONE_NUMBER);
            return StringUtils.join(items, StrUtil.C_SPACE);
        } else {
            return MaskingTool.mask(data, MaskingType.OTHERS);
        }
    }
}
