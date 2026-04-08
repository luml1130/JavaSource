package com.luml.java.jdkNewFeature.jdk18.api.stream;

import com.google.common.collect.Lists;
import com.luml.domain.Person2;
import com.luml.java.jdkNewFeature.jdk18.domain.EventReportInfoPO;
import com.luml.java.jdkNewFeature.jdk18.domain.ExceptReportEventExtFileVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;

import javax.swing.text.html.parser.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/11/20
 */
public class StreamFilterTest {

    public static void main(String[] args) {
        Integer pOrgId = -185;
        Integer wyOrgId=-185;
        System.out.println(pOrgId.equals(wyOrgId));
    }

    @Test
    public void test(){
       /* EventReportQueryConditionDTO dto = new EventReportQueryConditionDTO();
        dto.setBusinessId(id);
        dto.setEventDicIdSet(Sets.newHashSet(1701, 1702));
        List<EventReportInfoPO> reportInfoPOList = eventReportDomainService.findList(dto);*/
        EventReportInfoPO reportInfoPO = new EventReportInfoPO();
        List<ExceptReportEventExtFileVO> extFileVOList = new ArrayList<ExceptReportEventExtFileVO>(){{
            add(new ExceptReportEventExtFileVO
                    ("/e6yun/e6yun3/waybillPlatform/2026-01/e630667a9f4b499db003a3467be0fd40.png",2,1,"前灌顶铅封","",1,"40085"));
            add(new ExceptReportEventExtFileVO
                    ("/e6yun/e6yun3/waybillPlatform/2026-01/55610e8e17be4a26a3bdfe1be16965f1.png",2,2,"后灌顶铅封","",1,"40086"));
        }};
        reportInfoPO.setExtFileList(extFileVOList);

        List<EventReportInfoPO> reportInfoPOList = Lists.newArrayList(reportInfoPO);
                String sealNo = "";
        if (CollectionUtils.isNotEmpty(reportInfoPOList)) {
            sealNo = reportInfoPOList.stream()
                    .filter(x -> CollectionUtils.isNotEmpty(x.getExtFileList()))
                    .flatMap(x -> x.getExtFileList().stream())

                    .map(ExceptReportEventExtFileVO::getSealNo)
                    .filter(StringUtils::isNotBlank)
                    .distinct()
                    .collect(Collectors.joining("/"));
        }
        System.out.println(sealNo);
        //waybillDetailVO.setSealNo(sealNo);
    }

    @Test
    public void JiaoJiTest(){
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 =  Arrays.asList("banana", "date", "fig");

        boolean hasIntersection = list1.stream()
                .anyMatch(list2::contains);

        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("Has Intersection: " + hasIntersection);

    }

    //过滤字符串列表中的特定条件
    public static void main2(String[] args) {
        List<String> words = Arrays.asList("apple",
                "banana", "cherry", "date", "elderberry");
        // 过滤出所有以 'a' 开头的字符串
        List<String> startsWithA = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(startsWithA); // 输出: [apple, elderberry]
    }

    //3、使用 filter 方法过滤偶数
    public static void main3(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNumbers); // 输出: [2, 4, 6, 8, 10]
    }
    @Test
    public void otherMapFilterTest(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};
        List<Integer> list2 = new ArrayList<>();
        list2.add(30);
        list2.add(50);

        list =  list.stream()
                //list2如果为null 不影响
                .filter(x -> !list2.contains(x.getSalary()))
                .collect(Collectors.toList());
        System.out.println(list);

    }
    @Test
    public void afterNullFilterTest(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
        }};
        List<Person2> newList =  list.stream().
                filter(Person2 -> Person2.getGender() == 1 )
                .filter(Person2 -> Person2.getNickName().startsWith("x"))
                .collect(Collectors.toList());
        System.out.println(newList);
    }

    //两次filter是且的关系哦
    @Test
    public void dobuleFilterTest(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};
        List<Person2> newList =  list.stream().
                filter(Person2 -> Person2.getGender() == 1 )
                .filter(Person2 -> Person2.getNickName().startsWith("x"))
                .collect(Collectors.toList());
        System.out.println(newList);
        //[Person2{id=null, name='小刘', nickName='xiaoliu', gender=1, salary=30, amount=null}]
    }

    @Test
    public void filterTest() {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        //Set<Integer> vehicleIds = resultRecords.stream().map(EventReportInfoPO::getVehicleId).filter(Objects::nonNull).collect(Collectors.toSet());

        List<Person2> newList =  list.stream().
                filter(Person2 -> Person2.getGender() == 1 )
                .filter(Person2 -> Person2.getNickName().startsWith("x"))
                .peek(Person2 -> Person2.setAmount(new BigDecimal(0.22)))
                .collect(Collectors.toList());
               //.forEach(System.out::println);
        System.out.println(newList);
        //Person{name='小刘', nickName='xiaoliu', gender=1, salary=30}

        Person2 p = list.stream().
                filter(Person2 -> Person2.getGender() == 1 )
                .filter(Person2 -> Person2.getNickName().startsWith("x"))
                //.filter(Person2 -> StringUtils.isNotBlank(Person2.getNickName()) )
                .collect(Collectors.toList()).get(0);
        //System.out.println(p);


        /*List<Person2> list2 = list.stream().
                filter(Person2 -> Person2.getGender() ==1 )
                //filter((s) -> s.startsWith("a"))
                .collect(Collectors.toList());*/

    }


    @Test
    public void mapFilter(){
        // 创建一个Map
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 90);
        scores.put("Charlie", 70);
        scores.put("Diana", 95);
        scores.put("Ethan", 60);

        // 过滤出分数大于80的条目
        Map<String, Integer> filteredScores = scores.entrySet().stream()
                .filter(entry -> entry.getValue() > 80)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // 输出结果
        System.out.println(filteredScores);

        String key =
                scores.entrySet().stream()
                        .filter(entry -> entry.getValue() == 98)
                        //entry.getValue().equals()
                        .findFirst().get().getKey();


    }
}
