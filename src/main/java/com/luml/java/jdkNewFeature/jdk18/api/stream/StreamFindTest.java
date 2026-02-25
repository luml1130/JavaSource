package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person2;
import org.apache.commons.collections.IterableMap;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luml
 * @description
 * findAny() 是 Java 8 引入的 Stream API 中的一个方法，
 *              用于从流中获取任意一个元素并返回一个包含该元素的 Optional 对象
 *              非确定性行为‌：findAny() 不保证返回特定顺序的元素，尤其在并行流中。
 *              ‌返回类型‌：始终返回 Optional<T>，即使流为空也返回 Optional.empty()。
 *              ‌性能优势‌：在并行流中，findAny() 可以更快地终止搜索。
 *           最佳实践‌
 * ‌                并行流场景‌：优先使用 findAny() 以提升性能（见）。
 *                  ‌顺序要求‌：需顺序时使用 findFirst()（见）。
 *                  ‌空值处理‌：始终通过 Optional 处理结果
 * @date 2025/12/5
 */
public class StreamFindTest {

    /**
     * 用于从流中获取第一个元素并返回一个包含该元素的 Optional 对象
     * 核心特性‌
     *     ‌返回类型‌：始终返回 Optional<T>，即使流为空也返回 Optional.empty()。
     *     ‌顺序保证‌：在有序流（如 List 或排序后的流）中，findFirst() 返回第一个元素；在无序流（如 Set）中行为不确定。
     *     ‌短路操作‌：findFirst() 是短路终端操作，一旦找到匹配元素即终止流处理。
     * 最佳实践‌
     *     ‌顺序要求‌：需顺序时使用 findFirst()。
     *     ‌空值处理‌：始终通过 Optional 处理结果。
     *     ‌性能优化‌：在大数据集上，结合短路操作符（如 anyMatch()）提升性能。
     */
    @Test
    public void findFirst(){
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "mango");

        //1、 使用 findFirst() 获取第一个元素
        Optional<String> firstFruit = fruits.stream()
                .filter(fruit -> fruit.startsWith("a"))
                .findFirst();
        // 处理结果
        firstFruit.ifPresent(System.out::println); // 输出: apple


        //2、操作对象
        List<Person2> person3List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
        }};
        /*Optional<String> root = person3List.stream()
                        .map(Person2::getName)
                        .findFirst();*/
        String firstName = person3List.stream()
                //.filter(f->f.getAmount().equals(1))
                .map(Person2::getName)
                .findFirst().orElse(null);
        System.out.println("firstName="+firstName);
       // Integer rootId = root.map(BaseE6OrganizationTreeVO::getPid).orElse(NumberUtils.INTEGER_ZERO);
        /*public static CustomAreaTypeImportEnum getEnum(String label) {
            return Arrays.stream(values()).filter(item-> item.label.equals(label)).findFirst().orElse(null);
        }*/
        //Optional<BaseE6OrganizationVO> first = dept.stream().filter(item -> orgId.equals(item.getOrgId())).findFirst();
        //3、去重
        List<Person2> person4List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
            add(new Person2("张三",1,new BigDecimal(12.124)));
        }};
        String goodsName = person4List.stream()
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.joining(","));
        System.out.println("goodsName="+goodsName);

    }

    /**
     * 是 Java 8 引入的 Stream API 中的一个方法，用于从流中获取任意一个元素并返回一个包含该元素的 Optional 对象
     * 核心特性‌
     *     ‌非确定性行为‌：findAny() 不保证返回特定顺序的元素，尤其在并行流中（见）。
     *     ‌返回类型‌：始终返回 Optional<T>，即使流为空也返回 Optional.empty()（见）。
     *     ‌性能优势‌：在并行流中，findAny() 可以更快地终止搜索（见
     * 最佳实践‌
     *     ‌并行流场景‌：优先使用 findAny() 以提升性能（见）。
     *     ‌顺序要求‌：需顺序时使用 findFirst()（见）。
     *     ‌空值处理‌：始终通过 Optional 处理结果（见）。
     */
    @Test
    public void findAny(){
        // 创建一个整数流
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5).stream();

        // 使用 findAny() 获取流中的任意元素
        Optional<Integer> result = stream.findAny();

        // 检查是否有结果并打印
        result.ifPresent(System.out::println); // 输出可能是流中的任何一个元素


        List<String> fruits = Arrays.asList("apple", "banana", "orange", "mango");
        //findAny() 在串行流中可能返回第一个元素（见），但在并行流中行为不确定
        // 串行流：anyFruit 通常返回 "apple"
        Optional<String> anyFruit = fruits.stream()
                                .filter(f -> f.startsWith("a"))
                                .findAny();
        System.out.println("anyFruit="+anyFruit);//anyFruit=Optional[apple]

        // 并行流：anyFruitParallel 可能返回 "apple" 或 "orange"
        Optional<String> anyFruitParallel = fruits.parallelStream()
                                .filter(f -> f.startsWith("a"))
                                .findAny();
        System.out.println("anyFruitParallel="+anyFruitParallel); //anyFruitParallel=Optional[apple]

        //操作对象list
        //Optional<BaseE6OrganizationVO> organizationPo =
                //baseOrganizationPoList.stream().filter(o -> Objects.equals(orgId, o.getOrgId())).findAny();
        //空值判断
        /*if (!organizationPo.isPresent()) {
            log.warn("根据orgId在map中未查询出数据orgId={}", orgId);
            return baseOrganizationTreeVOList;
        }*/
    }

    /**
     * Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。
     * anyMatch  allMatch  noneMatch
     * 所有的匹配操作都是最终操作，并返回一个boolean类型的值。
     */
    @Test
    public void match(){
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false
        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);      // true

        //return *****(对象list).stream().anyMatch(indi -> indi.getCounts() > 0);

    }


    //对象判断
    @Test
    public void anyMatch(){
       Person2 p2 = new Person2("张三","zhangsan",0,10);
       List<Person2> p2List = new ArrayList<>();
       p2List.add(p2);
        boolean isHaveSyncDate =
                p2List
                        .stream()
                        .anyMatch((s) -> p2List.equals(s.getGender()));
        System.out.println(isHaveSyncDate);
        if (isHaveSyncDate) {
            System.out.println("包含了不能");
        }
    }


    //去重
    @Test
    public void distinctObject(){
        List<Person2> person3List = new ArrayList<Person2>(){{
            add(new Person2(1,"张三",0));
            add(new Person2(2,"李四",1));
            add(new Person2(3,"张三",1));
        }};

        // 保留第一个出现的元素
        List<Person2> uniquePersonList = person3List.stream()
                .collect(Collectors.toMap(
                        Person2::getName,           // 键：学生ID
                        Function.identity(),      // 值：学生对象本身
                        (existing, replacement) -> existing, // 冲突时保留第一个
                        LinkedHashMap::new        // 保持插入顺序
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
        //System.out.println(uniquePersonList);

        Map<String,Person2> uniquePersonMap = person3List.stream()
                .collect(Collectors.toMap(
                        Person2::getName,           // 键：学生ID
                        Function.identity(),      // 值：学生对象本身
                        (existing, replacement) -> existing, // 冲突时保留第一个
                        LinkedHashMap::new        // 保持插入顺序
                ))
                .values()
                .stream()
                .collect(Collectors.toMap(Person2::getName, item-> item));
        //System.out.println(uniquePersonMap);

        Map<String,Person2> uniquePersonMap2 = person3List.stream()
                .collect(Collectors.toMap(
                        Person2::getName,
                        Function.identity(),      // 值：学生对象本身
                        (existing, replacement) -> existing, // 冲突时保留第一个
                        LinkedHashMap::new        // 保持插入顺序
                 ));
        System.out.println(uniquePersonMap2);
    }


}
