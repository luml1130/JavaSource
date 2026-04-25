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
 * 脱敏姓名列表
 * 数据举例：张三,张四,张五
 * 业务场景：电子路书排班管理 送货员
 *
 */
public class DesensitizePersonalNameList extends DesensitizeOperator {

    public static final String OPERATOR_NAME = "脱敏姓名列表";

    @Override
    public String desensitize(String data) {
        String[] items = StringUtils.split(data, StrUtil.C_COMMA);
        List<String> desensitizedList = Arrays.stream(items)
                .map(name -> MaskingTool.mask(name, MaskingType.NAME))
                .collect(Collectors.toList());
        return StringUtils.join(desensitizedList, StrUtil.C_COMMA);
    }
}
