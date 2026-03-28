package com.luml.java.jdkNewFeature.jdk18.function.optional;

import book.MultiThreadProgram.Part03.chapter01.interrupu05_10.waitOld.Add;
import com.luml.domain.Address;
import com.luml.domain.User2;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.Optional;

/**
 * @author luml
 * @description
 *
 *  //设置位置信息
 *      eventReportCmd.PositionInfo positionInfo = Optional.ofNullable(cmd.getPosition())
 *                          .orElse(new EventReportCmd.PositionInfo());
 *      Optional<BaseE6OrganizationVO> first = 。。。
 *      if (first.isPresent()) {
 *
 *      private String orderNoStr;
 *      plOrderParam.setOrderNoStr(
 *              Optional.ofNullable(amQry.getCriteria())
 *              .map(PageOrderAmQry::getOrderNo)
 *              .orElse(StringUtils.EMPTY));
 *
 *     String name = person2Optional.isPresent() ? person2Optional.get().getNickName() : StringUtils.EMPTY;
 *
 * @date 2025/12/27
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello");

        // 判断是否存在
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        // 使用 ifPresent
        optional.ifPresent(System.out::println);

        // 使用 orElse
        String result = optional.orElse("Default Value");

        // 使用 map
        Optional<String> upperCase = optional.map(String::toUpperCase);
    }

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

    @Test
    public void createTest(){
        //创建一个空的 Optional 实例。
        Optional<String> optionalValue = Optional.empty();
        System.out.println(optionalValue); //Optional.empty

        //Optional.of 创建一个包含非空值的 Optional。如果传入的是 null，会抛出 NullPointerException。
        Optional<String> opt = Optional.of("Hello"); // 正常 非空 Optional
        System.out.println(opt.get()); // 输出: Hello

        // Optional.ofNullable 创建一个可以包含 null 值的 Optional。如果传入的是 null，则返回一个空的 Optional。
        Optional<String> nonNullOpt = Optional.ofNullable("Hello");
        System.out.println(nonNullOpt.isPresent()); // true
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

        Optional<String> opt2 = Optional.ofNullable(null); // 可以为空的 Optional
        Optional<String> opt3 = Optional.empty(); // 空 Optional

        // 正确用法：非空值
        Optional<String> opt = Optional.of("Hello"); // 正常 非空 Optional
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

    /**
     * 主要作用是：如果 Optional 包含一个非空值，则将该值传递给传入的函数（Function），
     *          并返回一个新的 Optional 对象，
     * 语法定义：public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
     *      T：当前 Optional 中封装的值的类型。
     *      U：映射后的新值的类型。
     *      mapper：一个函数，接收 T 类型的值并返回 U 类型的值。
     */
    @Test
    public void mapTest(){
        User2 user2 = getUser();
        Optional<User2> personOptional = Optional.of(user2);
        //personOptional 中的 User2 对象通过 User2::getName 被转换成一个新的 Optional<String>，即 nameOptional。
        Optional<String> nameOptional = personOptional.map(User2::getName);
        System.out.println(nameOptional.get());//

        Optional<String> result = personOptional
                .map(User2::getName)        // 第一次 map：获取姓名
                .map(String::length)          // 第二次 map：获取姓名长度
                .map(String::valueOf);        // 第三次 map：将长度转换为字符串
        if(result.isPresent()){
            System.out.println(result.get()); // 名字的长度 4
        }

        /*Optional<String> result = orderData
                .map(data -> data.get("customerInfo")) // 返回 Optional<JsonNode>
                .map(customerInfo -> customerInfo.get(name)) // 若 get(name) == null → 整体转为 Optional.empty()
                .map(node -> node.textValue());*/

    }

    /**
     * map 和 flatMap 的一个重要区别在于返回值类型：
     *     map：返回的是 Optional<U>，其中 U 是函数返回的值类型。
     *     flatMap：返回的是 Optional<U>，但函数本身返回的是 Optional<U>，flatMap 会自动“解包”这个嵌套的 Optional。
     */
    @Test
    public void flatMapTest(){
        //1\
        //Optional<User> userOptional = Optional.of(new User());
        User2 user1 = new User2("Alice", Optional.of(new Address("Beijing","Chaoyang Road")));
        User2 user2 = new User2("Bob", Optional.empty());

        // 使用 flatMap 安全地获取用户地址的城市名
        Optional<String> city1 = Optional.of(user1)
                .flatMap(user21 -> user21.getAddress())  // 获取地址 Optional
                .flatMap(address -> address.getCity()); // 获取城市 Optional

        Optional<String> city2 = Optional.of(user2)
                .flatMap(user22 -> user22.getAddress())  // 获取地址 Optional
                .flatMap(address -> address.getCity()); // 获取城市 Optional

        // 输出结果
       // System.out.println("User1 city: " + city1.orElse("No city found")); //User1 city: Beijing
       // System.out.println("User2 city: " + city2.orElse("No city found")); //User2 city: No city found


    }

    @Test
    public void flatMapTest2(){
        User2 user3 = new User2("Alice", Optional.of(new Address("Beijing","Chaoyang Road")));
        Optional<User2> userOptional = Optional.of(user3);
        //嵌套
        Optional<String> addressStreetOptional = userOptional
                .flatMap(user -> user.getAddress().map(Address::getStreet));
        if(addressStreetOptional.isPresent()){
            System.out.println(addressStreetOptional.get());
        }
    }

    private User2 getUser(){
        return new User2(1, "luml");
    }

}
