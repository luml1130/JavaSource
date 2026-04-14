package com.luml.java.jdkNewFeature.jdk18.api.stream;

import book.MultiThreadProgram.Part03.chapter01.producer11.p_r_test.C;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.luml.domain.Person2;
import com.luml.domain.User2;
import com.luml.java.data.Person;
import com.luml.java.jdkNewFeature.jdk18.Fruit;
import com.luml.java.jdkNewFeature.jdk18.FruitDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class StreamTransferTest {

    @Test
    public void arrayTransferTest(){
        // 示例字符串列表
        List<String> stringList = Arrays.asList("1", "2", "3", "4", "5");

        // 使用Stream将String转换为Integer
        List<Integer> integerList = stringList.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // 输出结果
        System.out.println(integerList);



    }

    @Test
    public void objectTransferTest(){
        java.util.List<User2> user2List = new ArrayList<User2>(){{
            add(new User2(1,"老鲁"));
            add(new User2(2,"桃子"));
            //add(new Person2("四木","simu",0,20));
        }};
        List<String> idStringList = user2List.stream()
                .map(User2::getId)
                .map(String::valueOf)
                //.map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(idStringList);
        //对字段加工
       /* waybillInfoDTOList.stream()
                .map(dto -> UserCacheUtils.getVehicle(dto.getVehicleId()))
                .distinct()
                .collect(Collectors.toList());*/
    }

    @Test
    public  void objectToFiledList(){
        java.util.List<User2> user2List = new ArrayList<User2>(){{
            add(new User2(1,"老鲁"));
            add(new User2(2,"桃子"));
            //add(new Person2("四木","simu",0,20));
        }};

        List<String> names = user2List.stream()
                .map(User2::getName)
                .collect(Collectors.toList());
        System.out.println(names); //[老鲁, 桃子]

    }

    public  void smallListTranAndToBigList(){

    }

    /**
     * Collectors.toMap 是 Java Stream API 中一个非常实用的终端操作，用于将流中的元素转换为 Map 集合。
     */
    @Test
    public void testToMap(){
        //java.util.List<E6Pair<Integer, Long>> statusList = waybillInfoMapper.getStatusSummary(findWaybillPageParam(criteria));
        //Map<Integer, Integer> statusMap = statusList.stream().collect(Collectors.toMap(E6Pair::getFirst, item -> item.getSecond().intValue()));
        java.util.List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
            //add(new Person2("四木","simu",0,20));
        }};
        List<User2> existFixedLineAreaList = new ArrayList<>();
        User2 user2 = new User2(74, "wo");
        User2 user3 = new User2(92, "ni");
        existFixedLineAreaList.add(user2);
        existFixedLineAreaList.add(user3);

        Map<Integer, String> genderMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, item -> item.getNickName()));
        System.out.println(genderMap); //{0=sanmu, 1=xiaoliu}
        //如果key冲突 比如 两个gender为0 的 java.lang.IllegalStateException: Duplicate key sanmu

        Map<Integer, User2> customAreaMap =
                    existFixedLineAreaList.stream().collect(Collectors.toMap(User2::getId,item -> item));
        System.out.println("customAreaMap="+customAreaMap);

        //或者如下写法
        /*Map<Integer, String> studentMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, Person2::getNickName));*/

        /**
         * 2、处理键冲突
         * 当流中存在生成相同键的元素时，如果不用第三个参数处理冲突，会抛出 IllegalStateException。‌
         * ‌第三个参数 mergeFunction‌ 是一个 BinaryOperator<U>，用于定义发生键冲突时的处理策略：
         * (existing, replacement) -> replacement：保留最后出现的元素（推荐常用）。‌
         * (existing, replacement) -> existing：保留第一个出现的元素。‌
         * 也可以进行更复杂的合并，例如将值合并成列表或进行数值累加。‌
         */
        list.add(new Person2("四木","simu",0,20));
        Map<Integer, Person2> studentMap = list.stream()
                .collect(Collectors.toMap(Person2::getGender, Function.identity(),
                        (existing, replacement) -> replacement));
        System.out.println("studentMap=" + studentMap);
        //studentMap={0=Person{name='四木', nickName='simu', gender=0, salary=20},
        //            1=Person{name='小刘', nickName='xiaoliu', gender=1, salary=30}}

    }


    @Test
    public void testToMap2(){

        /**
         * 1、指定 Map 实现类型
         * 默认情况下，toMap 返回 HashMap。如果需要特定的 Map 行为（如保持插入顺序或按键排序），
         *             可以使用第四个参数 mapSupplier 来指定 Map 的构造器。‌
         */
        java.util.List<User2> user2List = new ArrayList<User2>(){{
            add(new User2(1,"老鲁"));
            add(new User2(2,"桃子"));
            //add(new Person2("四木","simu",0,20));
        }};
        Map<Integer, User2> user2Map = user2List.stream()
                .collect(Collectors.toMap(User2::getId,Function.identity(),
                        (e, r) -> r, LinkedHashMap::new));//TreeMap::new
        //(e, r) -> r, ==  (existing, replacement) -> replacement)
        //System.out.println("user2Map="+ user2Map);
        //user2Map={1=com.luml.domain.User2@12bb4df8, 2=com.luml.domain.User2@4cc77c2e}
        System.out.println("user2Map="+ JSON.toJSONString(user2Map));
        //user2Map={1:{"id":1,"name":"老鲁"},2:{"id":2,"name":"桃子"}}

        /**
         * 2、安全处理 null 值
         * 在 Java 8 中，keyMapper 或 valueMapper 生成 null 键或值会抛出 NullPointerException。‌
         *  常见的处理方式是在收集前过滤掉 null 元素，或在映射函数中将 null 替换为默认值。
         */
        List<String> strings = Arrays.asList("apple", null, "banana");
        Map<String, Integer> lengthMap = strings.stream()
                .filter(Objects::nonNull)// 不加这一行java.lang.NullPointerException
                .collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("lengthMap="+lengthMap);
        //lengthMap={banana=6, apple=5}

        // 将 null 值替换为默认值
        user2List.add(new User2(3,null));
        Map<Integer, String> safeMap = user2List.stream()
                .collect(Collectors.toMap(
                        User2::getId,
                        p -> p.getName() != null ? p.getName() : "Unknown",
                        (existing, replacement) -> replacement)
                );
        System.out.println("safeMap="+safeMap);
        //safeMap={1=老鲁, 2=桃子, 3=Unknown}


    }

    //mapToInt/mapToLong/mapToDouble   list/map-->int
    @Test
    public void mapToIntLong() {
        com.sun.tools.javac.util.List<Person> peopleList = com.sun.tools.javac.util.List.of(
                new Person(1, "Alice"),
                new Person(2, "Bob"),
                new Person(3, "Charlie")
        );
        //int totalTransit = peopleList.stream().mapToInt(WaybillTemperatureVO::getTotalTransit).sum();
        int totalTransit = peopleList.stream().mapToInt(Person::getId).sum(); //id相加
        System.out.println(totalTransit);
    }






    //list--> map
    @Test
    public void list2Map() {
        List<Person> peopleList = com.sun.tools.javac.util.List.of(
                new Person(1, 12,"Alice"),
                new Person(2, 15,"Bob"),
                new Person(3, 9,"Charlie")
        );
       /* Map<Integer, Person> personMap = people.stream()
                .collect(Collectors.toMap(Person::getId, p -> p));*/

        //list先排序 再转换tomap
        //Collections.sort(peopleList,Comparator.comparing(Person::getFullName, Comparator.naturalOrder()));


        System.out.println(peopleList);

        /*Map<Integer, String> ageToNameMap = peopleList.stream()
                .collect(Collectors.toMap(Person::getId, Person::getFullName));
        System.out.println(ageToNameMap);*/

        /*Map<Integer, Person> alarmStatPOMap = new HashMap<>();//Collections.emptyMap();
        people.stream().forEach(person -> {
            alarmStatPOMap.put(person.getId(), person);
        });
        System.out.println(alarmStatPOMap);*/

    }

    @Test
    public  void smallListToBigList(){
        List<String> nameList = Arrays.asList("Alice","Bob","Charlie","jake");

        List<Person> peopleList = com.sun.tools.javac.util.List.of(
                new Person(1, 12,"Alice"),
                new Person(2, 15,"Bob"),
                new Person(3, 9,"Charlie")
        );
       Map<String,Person> peopleMap =  peopleList.stream()
               .collect(Collectors.toMap(Person::getFullName, Person->Person));

        List<Person> bigPeopleList = nameList.stream()
                .map(x->{
                    Person person = peopleMap.get(x);
                    if(Objects.isNull(person)){
                        person = new Person(4, 45,x);
                    }
                    return person;
                })
                .collect(Collectors.toList());
        System.out.println(bigPeopleList); // "Alice","Bob","Charlie","jake" 四个人
    }

    @Test
    public  void MapToList(){
        Map<String,Integer> map = new HashMap<>();
        map.put("女性",11);
        map.put("男性",12);
        List<CountVo> countVoList =  map.entrySet().stream()
                .map(entry->{
                    CountVo countVo = new CountVo();
                    countVo.setName(entry.getKey());
                    countVo.setCount(entry.getValue());
                    return countVo;
                })
                .collect(Collectors.toList());
        System.out.println(countVoList);
    }


    @Data
    public static class CountVo{
        private String name;
        private Integer count;
    }


    @Test
    public void MapStreamToListExample(){
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("date", 4);

        // 从 Map 提取键或值的 Stream，然后转换为 List
        List<String> keys = map.keySet().stream().collect(Collectors.toList()); // 提取键到 List
        List<Integer> values = map.values().stream().collect(Collectors.toList()); // 提取值到 List
        List<Map.Entry<String, Integer>> entries = map.entrySet().stream().collect(Collectors.toList()); // 提取条目到 List<Map.Entry>

        // 输出 List<String>（键）和 List<Integer>（值）和 List<Map.Entry<String, Integer>>（条目）
        System.out.println(keys); // [apple, banana, cherry, date] (顺序可能不同)
        System.out.println(values); // [1, 2, 3, 4] (顺序可能不同)
        System.out.println(entries); // [(apple=1), (banana=2), (cherry=3), (date=4)] (顺序可能不同)
    }


}
