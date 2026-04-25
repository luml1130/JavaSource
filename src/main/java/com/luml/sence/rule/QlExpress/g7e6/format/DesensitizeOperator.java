package com.luml.sence.rule.QlExpress.g7e6.format;

import com.ql.util.express.Operator;
import org.springframework.util.Assert;

abstract public class DesensitizeOperator extends Operator {

    abstract public String desensitize(String data);

    @Override
    public Object executeInner(Object[] list) throws Exception {
        Assert.isTrue(list.length == 1, "请输入脱敏数据");
        if(list[0] instanceof String){
            return desensitize((String) list[0]);
        }
        return list[0];
    }
}
