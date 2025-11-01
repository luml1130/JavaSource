package com.luml.java.data.json.fastjson.Serializer.e6yun;

import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.util.TypeUtils.fnv1a_64_magic_hashcode;
import static com.alibaba.fastjson.util.TypeUtils.fnv1a_64_magic_prime;

/**
 * @author xiqiang@e6yun.com
 */
public class TmsBaseEnumUtil {

    /**
     * key-枚举类class
     * value-保存id和枚举类的映射关系(key - 枚举类id， value - 枚举类)
     */
    private static final ConcurrentHashMap<Class<? extends Enum<?>>, Map<Integer, Enum<?>>> ENUM_ID_MAP = new ConcurrentHashMap<>(32);

    /**
     *  key   - {@link PropertyKey}
     *  value - 属性枚举 MAP
     *          key - 属性
     *          value - 枚举
     */
    private static final ConcurrentHashMap<PropertyKey<? extends Enum<?>>, Map<?, Enum<?>>> ENUM_PROPERTY_MAP = new ConcurrentHashMap<>(64);


    /**
     *  key   - {@link PropertyKey}
     *  value - 属性枚举 MAP
     *          key - 属性
     *          value - 枚举
     */
    private static final ConcurrentHashMap<Class<? extends Enum<?>>, EnumDeserializer> ENUM_DESERIALIZER_MAP = new ConcurrentHashMap<>(64);

    private TmsBaseEnumUtil() { }

    /**
     * 根据Id获取枚举类本身
     * @param id ID
     * @param type 枚举类型
     * @param <E> 枚举类，且实现 {@link TmsBaseIdEnum}
     * @return 枚举类
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E getById(int id, @NonNull Class<E> type) {

        Assert.isTrue(TmsBaseIdEnum.class.isAssignableFrom(type), "枚举类型必须实现" + TmsBaseIdEnum.class.getName());

        if (!ENUM_ID_MAP.containsKey(type)) {
            ENUM_ID_MAP.put(type, buildIdMap(type));
        }

        return Optional.ofNullable(ENUM_ID_MAP.get(type))
                .map(map -> (E) map.get(id))
                .orElse(null);
    }

    /**
     * 对于实现了 {@link TmsBaseLabelEnum} 接口的枚举类，直接根据Label找到枚举类型。
     * <b>会进行国际化转换！！！</b><br>
     * @param type 枚举类型
     * @param label label
     * @param <E> 枚举类型
     * @return 枚举类
     */
    public static <E extends Enum<E>> E getByLabel(String label, Class<E> type) {
        Assert.isTrue(TmsBaseLabelEnum.class.isAssignableFrom(type), "枚举类型必须实现" + TmsBaseLabelEnum.class.getName());
        return getByLabel(label, type, TmsBaseEnumUtil::labelGetter);
    }


    /**
     * 用枚举类name()取枚举类
     * @param name 枚举类name()
     * @param type 枚举类型
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>> E getByEnumName(String name, Class<E> type) {

        if (!ENUM_DESERIALIZER_MAP.containsKey(type)) {
            ENUM_DESERIALIZER_MAP.put(type, new EnumDeserializer(type));
        }

        EnumDeserializer deserializer = ENUM_DESERIALIZER_MAP.get(type);

        long hash = fnv1a_64_magic_hashcode;
        long hash_lower = fnv1a_64_magic_hashcode;
        for (int j = 0; j < name.length(); ++j) {
            char ch = name.charAt(j);

            hash ^= ch;
            hash_lower ^= ((ch >= 'A' && ch <= 'Z') ? (ch + 32) : ch);

            hash *= fnv1a_64_magic_prime;
            hash_lower *= fnv1a_64_magic_prime;
        }

        E e = (E) deserializer.getEnumByHashCode(hash);
        if (e == null && hash_lower != hash) {
            e = (E) deserializer.getEnumByHashCode(hash_lower);
        }

        return e;

    }

    /**
     * 枚举转id
     * @param baseEnum
     * @param nullIfValue
     * @return
     */
    public static <E extends TmsBaseEnum> int convertId(E baseEnum, int nullIfValue) {
        return Optional.ofNullable(baseEnum).map(TmsBaseIdEnum::getId).orElse(nullIfValue);
    }

    /**
     * 多个枚举转id list
     * @param enumList
     * @return
     */
    public static <E extends TmsBaseEnum> List<Integer> convertIdList(List<E> enumList) {
        List<Integer> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(enumList)) {
            result.addAll(enumList.stream().map(TmsBaseEnum::getId).collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * 通过枚举类型的任意 {@link String} 类型属性，获取枚举类。<br>
     * <b>会进行国际化转换！！！</b><br>
     * 如果不希望该{@link String} 类型做国际化转换，请使用 {@link TmsBaseEnumUtil#getByProperty(Object, Class, Function)} 方法
     * @param type 枚举类型
     * @param label String属性
     * @param getter 该String属性的getter方法
     * @param <E> 枚举类型
     * @return 枚举类
     */
    public static <E extends Enum<E>> E getByLabel(String label, Class<E> type, Function<E, String> getter) {
        // TODO: 国际化转换
        return getByProperty(label, type, getter);
    }

    /**
     * 通过枚举类型的任意 {@link String} 类型属性，获取枚举类。<br>
     * 和 {@link TmsBaseEnumUtil#getByLabel(String, Class, Function)} 的区别是，当本方法传入String类型时，并不会做国际化转化<br>
     * @param type 枚举类型
     * @param param 任意属性
     * @param getter 该属性的getter方法
     * @param <E> 枚举类型
     * @param <T> 属性的类型
     * @return 枚举类
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<E>, T> E getByProperty(T param, Class<E> type, Function<E, T> getter) {
        PropertyKey<E> propertyKey = new PropertyKey<>(type, getter);
        if (!ENUM_PROPERTY_MAP.containsKey(propertyKey)) {
            ENUM_PROPERTY_MAP.put(propertyKey, buildPropertyMap(type, getter));
        }

        return Optional.ofNullable(ENUM_PROPERTY_MAP.get(propertyKey))
                .map(map -> (E) map.get(param))
                .orElse(null);
    }

    /**
     * 构建属性 map
     * @param type 枚举类
     * @param getter 属性的Getter方法
     * @param <E> 枚举类
     * @return 属性MAP
     */
    private static <E extends Enum<E>, T> Map<T, Enum<?>> buildPropertyMap(Class<E> type, Function<E, T> getter) {
        return EnumSet.allOf(type)
                .stream()
                .collect(Collectors.toMap(getter, e -> e));
    }

    /**
     * 构建ID map
     * @param type 枚举类
     * @param <E> 枚举类，且实现 {@link TmsBaseIdEnum}
     * @return key - 枚举类id， value - 枚举类
     */
    private static <E extends Enum<E>> Map<Integer, Enum<?>> buildIdMap(Class<E> type) {
        return EnumSet.allOf(type)
                .stream()
                .collect(Collectors.toMap(e -> ((TmsBaseIdEnum) e).getId(), e -> e));
    }

    /**
     * 给TmsLabelEnum的子类提供getLabel方法
     * @param e 枚举
     * @param <E> 枚举类型
     * @return getter方法
     */
    private static <E extends Enum<E>> String labelGetter(E e) {
        return ((TmsBaseLabelEnum) e).getLabel();
    }


    @EqualsAndHashCode(of = {"type", "getter"})
    @AllArgsConstructor
    private static class PropertyKey<E extends Enum<E>> {

        @Getter
        Class<E> type;

        @Getter
        Function<E, ?> getter;

    }
}
