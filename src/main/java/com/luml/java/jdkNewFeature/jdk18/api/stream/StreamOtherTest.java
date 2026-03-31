package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.luml.domain.Person;
import com.luml.domain.Person2;
import com.luml.java.jdkNewFeature.jdk18.Fruit;
import com.luml.java.jdkNewFeature.jdk18.FruitDto;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * 聚合、分组
 * counting()：统计元素数量。
 * averagingInt/Double/Long()：计算平均值。
 * summingInt/Double/Long()：求和。
 * summarizingInt/Double/Long()：一次性获取统计信息（如最大值、最小值、总和等）。
 *
 * joining()：将元素连接为字符串，可指定分隔符。
 *
 * reducing()：自定义归约逻辑（如计算总和并减去固定值）。
 *  Java 8 引入的一个收集器方法，用于将流（Stream）中的元素通过累积操作合并成一个单一的结果，常用于求和、求最大值、最小值等聚合操作。‌1
 * @date 2025/12/5
 */
public class StreamOtherTest {

    /**
     * orElse 与 orElseGet 的区别
     *  ‌orElse(T other)‌：无论 Optional 是否包含值，都会立即计算并返回默认值。适用于默认值计算成本较低的情况。
     *  ‌orElseGet(Supplier<T> supplier)‌：仅在 Optional 为空时才调用 Supplier 来生成默认值。
     *          适用于默认值计算成本较高的情况，能够避免不必要的计算。
     */
    @Test
    public  void orElseGetTest(){
        List<Integer> list = Arrays.asList(10, 20, 30);

        // 使用 orElse - 即使不需要默认值，也会计算
        int a = list.stream().reduce(Integer::sum).orElse(get("a"));
        System.out.println(a); //60

        // 使用 orElseGet - 仅在需要时才计算默认值
        int b = list.stream().reduce(Integer::sum).orElseGet(() -> get("b"));
        System.out.println(b);//60
        //get("a") 会被立即执行，而 get("b") 只有在 Optional 为空时才会执行。
    }
    private static int get(String iam){
        return 1;
    }
    @Test
    public void orElseTest(){
        List<Person2> personList = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,100,false));
            add(new Person2("李四","zhangsan",1,200,false));
        }};
        int dataTerminalId = personList.stream().filter(Person2::isSelect).findFirst()
                .map(Person2::getSalary)
                //.orElseGet()
               // .orElseThrow()
                .orElse(null);
        System.out.println(dataTerminalId); //200

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        String result = names.stream()
                .filter(name -> name.equals("David"))
                .findFirst()
                .orElse("Unknown");
        System.out.println(result); // 输出: Unknown

        //User user = null;
        //User result1 = Optional.ofNullable(user).orElse(new User()); // new User() 会被立即执行
        //User result2 = Optional.ofNullable(user).orElseGet(User::new); // 仅在 user 为 null 时才创建 User 对象
       // 在上述示例中，orElse 会始终执行 new User()，而 orElseGet 则会在需要时才调用 User::new，从而提升性能。


    }


    //reducing()
    @Test
    public void reduceTest(){
        //拼接
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        Optional<String> reduced =
                stringCollection
                        .stream().sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);//apple#banana#cherry#date#elderberry
    }

    @Test
    public  void NumCount(){
        //求和（无初始值）‌
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> sum = numbers.stream().collect(Collectors.reducing((a, b) -> a + b));
        sum.ifPresent(System.out::println); // 输出: 15

        //求和（有初始值）‌
        int sum2 = numbers.stream().collect(Collectors.reducing(2, (a, b) -> a + b));
        System.out.println(sum2); // 初始值是2 输出: 17

        //求最大值/最小值‌
        Optional<Integer> max = numbers.stream().collect(Collectors.reducing(Integer::max));
        max.ifPresent(System.out::println); // 输出: 5

        Optional<Integer> min = numbers.stream().collect(Collectors.reducing(Integer::min));
        min.ifPresent(System.out::println); // 输出: 1

    }

    @Test
    public void objectCount2() {
        List<Person2> personList = new ArrayList<Person2>() {{
            add(new Person2("张三", "zhangsan", 0, 100));
            //add(new Person2("李四", "zhangsan", 1, 200));
        }};

       /*String aa =  personList.stream()
                .map(Person2::getSalary)
                .collect(Collectors.joining(","));
        System.out.println(aa);*/



    }
    @Test
    public void objectCount(){
        List<Person2> personList = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,100));
            add(new Person2("李四","zhangsan",1,200));
        }};
        //1、初始值100 +（100-5000) + (200-5000) =
        Integer sumSal = personList.stream()
                .collect(Collectors.reducing(100, Person2::getSalary, (x, y) -> x + y - 5000));
        System.out.println(sumSal); //-9600

        //2、Integer
        Integer integerSum = personList.stream()
                .map(Person2::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("integerSum="+integerSum); //integerSum=300

        //3、bigDecimalSum
        List<Person2> person2List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
        }};
        Integer bigDecimalSum = person2List.stream()
                .map(Person2::getAmount)
                .mapToInt(BigDecimal::intValue)
                .sum();
        System.out.println("bigDecimalSum="+bigDecimalSum);//bigDecimalSum=24

        //4、使用reduce方法来计算总和
        // 创建一个BigDecimal的列表
        List<BigDecimal> numbers = Arrays.asList(
                new BigDecimal("10.5"),
                new BigDecimal("20.3"),
                new BigDecimal("35.7")
        );
        //4.1、使用reduce方法来计算总和
       BigDecimal bigDecimalSum2 = numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("bigDecimalSum2="+bigDecimalSum2);

        //4.2、Object
        List<Person2> person3List = new ArrayList<Person2>(){{
            add(new Person2("张三",0,new BigDecimal(12.123)));
            add(new Person2("李四",1,new BigDecimal(12.124)));
        }};
        List<BigDecimal> amountList = person3List.stream()
                .map(Person2::getAmount)
                .collect(Collectors.toList());
        BigDecimal objectDecimalSum = amountList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                //保留3位 四舍五入
                .setScale(3, RoundingMode.HALF_UP);
        System.out.println("objectDecimalSum="+objectDecimalSum);
    }

    //counting()：统计元素数量。
    @Test
    public void countTest() {
        List<String> stringCollection = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);    // 3
    }

    //.joining 拼接
    @Test
    public  void joinTest(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        String names = list.stream()
                .map(p -> p.getName())
                .collect(Collectors.joining(","));
        System.out.println(names); //张三,李四,王五,小刘,三木



    }

    //averagingInt/Double/Long()
    @Test
    public void averagingDoubleTest() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setAge(12);
        personList.add(person);
        Person person2 = new Person();
        person.setAge(15);
        personList.add(person2);

        Double avgAge = personList.stream()
                .collect(Collectors.averagingDouble(Person::getAge));
        System.out.println(avgAge);
    }

    @Test
    public void forEachTest(){
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        /*map.entrySet().forEach(
                entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue())
        );
        map.values().forEach(
                value -> System.out.println("Value = " + value)
        );*/
        //等价于
        map.values().forEach(System.out::println);//2132

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.parallelStream()
                .forEach(n -> System.out.print(n + " ")); // 输出可能：3 1 4 2 5
        nums.parallelStream()
                .forEachOrdered(n -> System.out.print(n + " ")); // 输出：1 2 3 4 5



    }

    /**
     * Stream.peek() 是 Java 8 引入的 ‌Stream API 中的一个中间操作‌，
     *      主要用于在流处理过程中‌观察或调试元素状态‌，而‌不会修改流中的元素本身‌。
     * 核心特性
     *     ‌中间操作（Intermediate Operation）‌： 不会触发流的执行，必须配合终端操作（如 collect()、forEach()）才能生效。
     *     ‌不改变流内容‌：仅用于旁路观察，适合调试、日志记录等场景。
     *     ‌惰性求值‌：只有在终端操作被调用时，peek 中的逻辑才会执行。
     *     ‌接收 Consumer<T> 参数‌：该函数无返回值（void），通常用于执行副作用（如打印、日志、状态检查）。
     * 典型使用场景
     *     ‌调试流处理过程‌:查看某一步操作后的元素状态。
     *     ‌记录日志‌:在数据处理链中记录关键节点的值。
     *     ‌数据验证或检查‌:在不中断流的前提下，对元素进行条件判断并输出警告。
     */
    @Test
    public void peekTest1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)           // 过滤偶数
                .peek(n -> System.out.println("Filtered: " + n))  // 调试点1
                .map(n -> n * n)                   // 平方
                .peek(n -> System.out.println("Mapped: " + n))    // 调试点2
                .collect(Collectors.toList());

        System.out.println("Result: " + result);

    }
    @Test
    public void peekTest(){
       /* Map<WaybillNodeOriginDocDTO, String> originDocNoMap = waybillOriginDocInfoGateway.findBatchOriginDocNo(sourceMap.keySet());
        List<WaybillNodeOriginDocRelPO> list = sourceMap.entrySet().stream()
                .map(entry -> {
                    WaybillNodeOriginDocRelPO po = new WaybillNodeOriginDocRelPO();
                    po.setId(entry.getValue());
                    po.setWaybillId(waybillId);
                    po.setWaybillNodeId(waybillNodeEntity.getId());
                    po.setOriginDocType(entry.getKey().getOriginDocType());
                    po.setOriginDocId(entry.getKey().getOriginDocId());
                    po.setOriginNodeType(entry.getKey().getNodeType());
                    po.setOriginDocNo(StringUtils.isBlank(entry.getKey().getOriginDocNo()) ? originDocNoMap.get(entry.getKey()) : entry.getKey().getOriginDocNo());
                    return po;
                })
                // 内存
                .peek(po -> relMap.put(po.getId(), po))
                .collect(Collectors.toList());*/
        FruitDto fruitDto = new FruitDto("apple",1);
        FruitDto fruitDto1 = new FruitDto("banana",2);
        FruitDto fruitDto2 = new FruitDto("cherry",3);
        FruitDto fruitDto3 = new FruitDto("orange",4);
        Map<FruitDto, String> originDocNoMap = new HashMap<>();
        originDocNoMap.put(fruitDto, "苹果");
        originDocNoMap.put(fruitDto1, "香蕉");
        originDocNoMap.put(fruitDto2, "草莓");
        originDocNoMap.put(fruitDto3, "橘子");

        List<Fruit>  fruitList = originDocNoMap.entrySet().stream()
                .map(entry ->{
                    Fruit fruit = new Fruit();
                    fruit.setName(entry.getKey().getFruitName());
                    fruit.setOrder(entry.getKey().getOrderNO());
                    fruit.setChiName(entry.getValue());
                    return fruit;
                })
                //.peek(po -> relMap.put(po.getId(), po))
                .collect(Collectors.toList());
        // System.out.println(fruitList); 其实已经OK了
    }

}
