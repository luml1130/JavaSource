package com.luml.java.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.luml.domain.City;

/**
 * @author luml
 * @description
 *  G7E6-busi模块里面 TmsBaseJsonListTypeHandler 也使用了
 *  ‌WriteMapNullValue‌：控制是否输出值为 null 的字段。默认为 false（不输出），设置为 true 时会输出 null 值。‌
 * ‌WriteNullStringAsEmpty‌：当字符串类型字段为 null 时，输出为空字符串 "" 而非 null。通常需配合 WriteMapNullValue 使用。‌
 * ‌WriteNullListAsEmpty‌：如果 List 字段为 null，输出 [] 而非 null。同样建议与 WriteMapNullValue 一起启用。‌
 * ‌WriteNullNumberAsZero‌：数值字段为 null 时输出 0，而非 null。‌
 * ‌WriteNullBooleanAsFalse‌：布尔字段为 null 时输出 false，而非 null。‌
 * ‌PrettyFormat‌：使输出的 JSON 结果格式化（如缩进换行），便于阅读，默认为 false。‌
 * ‌SortField‌：按字段名称的字典序排序后输出，有助于提升反序列化性能。‌
 * ‌WriteDateUseDateFormat‌：使用全局指定的日期格式（如 yyyy-MM-dd）输出日期字段，默认为 false。‌
 * ‌BeanToArray‌：将对象序列化为数组形式（仅输出属性值，不包含字段名），适用于特定场景。‌
 * ‌DisableCircularReferenceDetect‌：禁用循环引用检测，避免因对象循环引用导致的序列化问题。‌
 * @date 2025/12/5
 */
public class SerializerFeatureTest {

    public static void main(String[] args) {

        City obj = new City();
        obj.setProvince("陕西省");
        obj.setAddress("aaa");

        // 基础序列化
        String json = JSON.toJSONString(obj);
        System.out.println(json); //{"address":"aaa","province":"陕西省"}


        // 自定义序列化选项（可组合多个 Feature）
        String customJson = JSON.toJSONString(obj,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.PrettyFormat
        );
        System.out.println(customJson);
        /**
         * {
         * 	"address":"aaa",
         * 	"city":"",
         * 	"district":"",
         * 	"province":"陕西省"
         * }
         */
    }
}
