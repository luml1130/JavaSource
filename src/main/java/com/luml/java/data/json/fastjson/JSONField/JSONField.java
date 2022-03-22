package com.luml.java.data.json.fastjson.JSONField;

/**
 * FieldInfo可以配置在getter/setter⽅法或者字段上
 * 配置在field上
 *  // 配置date序列化和反序列使⽤yyyyMMdd⽇期格式
 *  使⽤serialize/deserialize指定字段不序列化
 *  使⽤ordinal指定字段的顺序:默认是根据fieldName的字母序进⾏序列
 * @author luml
 */
public @interface JSONField {
    // 配置序列化和反序列化的顺序，1.1.42版本之后才⽀持
    int ordinal() default 0;
    // 指定字段的名称
    String name() default "";
    // 指定字段的格式，对⽇期格式有⽤
    String format() default "";
    // 是否序列化
    boolean serialize() default true;
    // 是否反序列化
    boolean deserialize() default true;
}
