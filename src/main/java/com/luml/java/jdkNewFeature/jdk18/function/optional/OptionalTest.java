package com.luml.java.jdkNewFeature.jdk18.function.optional;

import com.luml.domain.User2;
import org.junit.Test;

import java.util.Optional;

/**
 * @author luml
 * @description
 *
 * @date 2025/12/27
 */
public class OptionalTest {

    /**
     * Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，
     * Optional 被定义为一个简单的容器，其值可能是null或者不是null。
     * 在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，
     * 而在Java 8中，不推荐你返回null而是返回Optional。
     */
    @Test
    public void test(){
        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    /**
     * orElse 是 Java 8 引入的 Optional 类中的一个方法，用于在 Optional 对象为空时提供默认值。
     * 核心特性‌
     *     ‌返回类型‌：始终返回指定类型的值（非 Optional）。
     *     ‌短路行为‌：如果 Optional 包含值，则直接返回该值；否则返回默认值。
     *     ‌避免空指针‌：通过提供默认值避免显式空值检查
     * 最佳实践‌
     *     ‌静态默认值‌：优先使用 orElse。
     *     ‌动态计算‌：使用 orElseGet。
     *     ‌空值处理‌：避免显式空值检查。
     * 注意‌：orElse 总是执行，而 orElseGet 只在需要时执行
     */
    @Test
    public void testOrElseAndOrElseGet(){

        // 使用 orElse 提供默认值
        Optional<String> optionalValue = Optional.empty();
        String result = optionalValue.orElse("Default Value");
        System.out.println(result); // 输出: Default Value

        // 使用 orElseGet
        Optional<String> optional = Optional.empty();
        String dynamicDefault = optional.orElseGet(() -> "Dynamic " + System.currentTimeMillis());
        System.out.println(dynamicDefault); //Dynamic 1766805312758

    }

    /**
     * Optional.of() 是 Java 8 引入的 Optional 类中的核心静态方法，用于创建一个包含非空值的 Optional 对象
     * 参数‌：必须为非空值 T value（不能为 null）。
     * ‌返回‌：包含指定值的 Optional<T> 对象。
     * 使用场景‌
     *     当确定值不会是 null 时使用（如方法返回值）。
     *     避免显式空值检查，通过 Optional 链式操作简化代码（见）
     * 最佳实践‌
     *     ‌非空值场景‌：优先使用 Optional.of()（见）。
     *     ‌空值处理‌：结合 orElse() 或 orElseGet() 提供默认值（见）。
     *     ‌避免显式空值检查‌：通过 Optional 链式操作简化代码（见
     * ofNullable与 of 的区别‌
     *      特性       ofNullable               of
     *      ‌参数类型‌   可能为 null 的值            必须为非 null 的值
     *      ‌行为‌      包装 null 返回空 Optional   包装 null 抛 NullPointerException
     *      ‌适用场景‌   处理可能为 null 的值         确保值非 null 时使用
     */
    @Test
    public void testOf(){
        // 正确用法：非空值
        Optional<String> opt = Optional.of("Hello"); // 正常
        System.out.println(opt.get()); // 输出: Hello

        // 错误用法：传入 null
        Optional<String> optNull = Optional.of(null); // 抛出 NullPointerException


        Optional<String> nullOpt2 = Optional.of(null);
        // 错误示例：直接使用 get()（见）
        String valueNull = nullOpt2.get(); // 可能抛 NoSuchElementException
        // 正确示例：使用 orElse() 替代
        String value = nullOpt2.orElse("Default");



    }

    /**
     * ofNullable 是 Java 8 引入的 Optional 类中的核心静态方法，
     *          用于安全地包装可能为 null 的对象，避免直接操作 null 导致的空指针异常（NPE）
     * 核心特性‌
     *     ‌返回类型‌：始终返回 Optional<T>，无论输入是否为 null。
     *     ‌避免 NPE‌：通过包装 null 值，避免显式空值检查（见）。
     *     ‌链式操作‌：结合 map、filter 等方法简化嵌套判断（见）。
     * 最佳实践‌
     *     ‌避免显式空值检查‌：优先使用 ofNullable（见）。
     *     ‌结合链式方法‌：简化嵌套判断（见）。
     *     ‌避免过度使用‌：仅用于方法返回值，非参数或字段（见
     */
    @Test
    public void testOfNullable(){
        // 包装非 null 值
        Optional<String> nonNullOpt = Optional.ofNullable("Hello");
        System.out.println(nonNullOpt.isPresent()); // true

        // 包装 null 值
        Optional<String> nullOpt = Optional.ofNullable(null);
        System.out.println(nullOpt.isPresent()); // false


        Optional<String> nullOpt2 = Optional.ofNullable(null);
        // 错误示例：直接使用 get()（见）
        String valueNull = nullOpt2.get(); // 可能抛 NoSuchElementException
        // 正确示例：使用 orElse() 替代
        String value = nullOpt2.orElse("Default");


        // 处理可能为 null 的方法返回值
        Optional<User2> userOpt = Optional.ofNullable(getUser());
        userOpt.ifPresent(user -> System.out.println(user.getName()));

        // 安全获取嵌套对象属性
        Optional<Integer> cityOpt = Optional.ofNullable(getUser())
                .map(User2::getId);
               // .map(User2::getAge);
        cityOpt.ifPresent(System.out::println);

    }

    private User2 getUser(){
        User2 user2 = new User2(1, "lumengliang");
        return user2 ;
    }

}
