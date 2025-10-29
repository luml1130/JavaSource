package com.luml.java.data.json.Handler;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author luml
 * @description
 * @date 2025/10/29
 */
public class TmsBaseFastJsonConfigs {

    public static final SerializerFeature[] SERIALIZER_FEATURE_CONFIGS;

    private TmsBaseFastJsonConfigs() {
    }

    static {
        SERIALIZER_FEATURE_CONFIGS = new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect};
    }
}
