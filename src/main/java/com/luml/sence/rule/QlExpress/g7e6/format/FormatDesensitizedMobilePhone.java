package com.luml.sence.rule.QlExpress.g7e6.format;

import cn.hutool.core.util.DesensitizedUtil;
import com.ql.util.express.Operator;
import com.ql.util.express.exception.QLException;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 格式化电话号码： 18612345678 => 186****5678
 *
 * @author xiqiang@e6yun.com
 * @date 2022/7/18
 */
@Deprecated
public class FormatDesensitizedMobilePhone extends Operator {

    public static final String NAME = "desensitizedMobilePhone";

    @Override
    public Object executeInner(Object[] objects) throws QLException {
        Assert.isTrue(objects.length == 1 && Objects.nonNull(objects[0]), "请输入手机号");
        return DesensitizedUtil.mobilePhone((String) objects[0]);
    }
}
