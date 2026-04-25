package com.luml.sence.rule.QlExpress.g7e6.format;

import com.alibaba.fastjson.JSONObject;
import com.ql.util.express.Operator;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 维护JSONObject的字段，可以在formatter脚本里面使用<br>
 * <p>
 * 新增或修改： updateColumn(jsonObj, 'key', newValue); <br>
 * 删除: updateColumn(jsonObj, 'key', null) <br>
 *
 * @author xiqiang@e6yun.com
 * @date 2022/7/18
 */
public class FormatUpdateColumn extends Operator {

    public static final String NAME = "updateColumn";

    @Override
    public Object executeInner(Object[] objects) throws Exception {
        Assert.isTrue(objects.length == 3, "必须有3个参数");

        Assert.isTrue(Objects.nonNull(objects[0]) && objects[0] instanceof JSONObject, "第一个参数必须是JSONObject");
        Assert.isTrue(Objects.nonNull(objects[1]) && objects[1] instanceof String, "第一个参数必须是String，为参数的key");

        JSONObject obj = (JSONObject) objects[0];
        String key = (String) objects[1];
        Object value = objects[2];

        if (Objects.isNull(value)) {
            // 删除JSON字段
            obj.remove(key);
        } else {
            // 更新或修改
            obj.put(key, value);
        }

        return obj;
    }
}
