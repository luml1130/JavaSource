package com.luml.source.java.jdkNewFeature.jdk8.functional.BaseFour.consumer;


import java.util.Objects;

/**
 * 自定义 BiConsumer 接口，模拟 java.util.function.BiConsumer
 * 代表一个接受两个输入参数且不返回结果的操作。
 *
 * @param <T> 第一个输入参数的类型
 * @param <U> 第二个输入参数的类型
 */
@FunctionalInterface
public interface CustomBiConsumer <T, U>{
    /**
     * 对给定的参数执行此操作。
     *
     * @param t 第一个输入参数
     * @param u 第二个输入参数
     */
    void accept(T t, U u);

    /**
     * 返回一个组合的 BiConsumer，按顺序执行当前操作，然后执行 after 操作。
     * 如果任一操作抛出异常，则将其中继到组合操作的调用者。
     * 如果执行当前操作抛出异常，则不会执行 after 操作。
     *
     * @param after 在当前操作之后执行的操作
     * @return 一个组合的 BiConsumer，先执行当前操作，再执行 after 操作
     * @throws NullPointerException 如果 after 为 null
     */
    default CustomBiConsumer<T, U> andThen(CustomBiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }
}

