package com.luml.java.data.json.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.List;

/**
 * @author luml
 * @description  ??? 这个干啥的
 * @date 2025/10/29
 */
public class BaseJsonListTypeHandler<E>{

    public BaseJsonListTypeHandler() {
    }

    public List<E> parse(String json) {
        return (List) JSON.parseObject(json, new TypeReference<List<E>>() {
        }, new Feature[0]);
    }

    protected String toJson(List<E> obj) {
        return JSONObject.toJSONString(obj, TmsBaseFastJsonConfigs.SERIALIZER_FEATURE_CONFIGS);
    }
}
