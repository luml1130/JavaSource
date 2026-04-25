package com.luml.sence.rule.QlExpress.g7e6.check;

import cn.hutool.core.util.StrUtil;
import com.ql.util.express.exception.QLException;

import java.util.Objects;

/**
 * 字符串长度<br>
 * length(str, min, max, message);  待校验的字符串、最小长度（null表示不限制）、最大长度、提示信息<br>
 * length(str, null, 20, "长度不能超过20位")
 * @author xiqiang@e6yun.com
 * @date 2022/8/10
 */
public class StrLength extends AbstractCheckOperator {

    private static final String NAME = "lengthStr";

    @Override
    protected void doCheck(Object[] list) throws QLException {

        checkParam(list);


    }

    private void checkParam(Object[] list) throws QLException {

        if (list.length != 4) {
            throw new QLException(StrUtil.format("{}参数不合法", this.getAliasName()));
        }

        if (!(list[0] instanceof String)) {
            throw new QLException(StrUtil.format("{}第1个参数必须为String类型", this.getAliasName()));
        }

        if (!(Objects.isNull(list[1]) || list[1] instanceof Integer)) {
            throw new QLException(StrUtil.format("{}第2个参数必须为Integer类型或Null", this.getAliasName()));
        }

        if (!(Objects.isNull(list[2]) || list[2] instanceof Integer)) {
            throw new QLException(StrUtil.format("{}第3个参数必须为Integer类型或Null", this.getAliasName()));
        }

        if (Objects.isNull(list[1])) {

        }

    }


}
