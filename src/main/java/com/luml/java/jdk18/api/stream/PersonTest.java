package com.luml.java.jdk18.api.stream;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.luml.domain.Person2;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2020/4/30 21:17
 */
public class PersonTest {

    public static void main(String[] args) {

        List<String> userList2 = new ArrayList<>();
        userList2.add("LuMengLiang");
        System.out.println(userList2.toString());

        String userListStr = JSONArray.toJSONString(userList2);//userList2.toString();
        System.out.println(userListStr);

        String userListStr2 = "[2]";//"[\"LuMengLiang\"]";

        //String userListStr = schoolTagInfo.getUserList();
        List<Long> userList = StringUtils.isBlank(userListStr2) ? null : JSON.parseArray(userListStr2,Long.class);
        //List<String> partyList = JSON.parseArray(partyListStr,String.class);
        //List<String> partyList = Arrays.asList(partyListStr.split(","));
        for(Long partyId : userList){
            System.out.println(partyId);
            //System.out.println(Long.valueOf(partyId));
        }
    }

    public static void main2(String[] args) {
        //Person2 person2 = new Person2();
        //System.out.println(person2.getName());

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
        System.out.println(list.toString());
        List<String> asList = Arrays.asList(list.toString());
        System.out.println("aa="+JSON.toJSONString(asList));
        for(String s : asList){
            System.out.println(s);
        }

        String json = JSONArray.toJSONString(list);//把list转换成json格式的String类型
        System.out.println("json格式的String类型 " + json);

        System.out.println(StringUtils.strip(list.toString(),"[]"));
    }

    @Test
    public  void test() {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};
        //从list对象中取一个值作为key 有去重的效果
        List<Integer> historyClassIds =  list.stream()
                .map(l -> l.getSalary())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("historyClassIds="+ historyClassIds);
        //下面这个不会去重哦
        List<Integer> historyClassIds2 =  list.stream()
                .distinct()
                .map(l -> l.getSalary())
                .collect(Collectors.toList());
        System.out.println("historyClassIds2="+ historyClassIds2);


        /**
         * min  ：求集合中最小对象:张三
         */
        Object obj = list.stream().min((p1,p2)->(p1.getSalary()-p2.getSalary())).get();
        System.out.println("obj="+obj);
        /**
         * max   : 求集合最大对象:三木
         */
        Object maxObj = list.stream().max((p1,p2)->(p1.getSalary()-p2.getSalary())).get();
        System.out.println(maxObj);
        //取集合中的前多少个元素
        list.stream().filter(
                (p)->(1==p.getGender())
        ).limit(5);

        /**
         * sorted :排序
         */
        list.stream().sorted(
                (p1,p2)->p1.getNickName().compareTo(p2.getNickName())
        ).forEach(
                x-> System.out.println(x.getName())
        );
        /**
         *  map:  归类，结果一般是一组数据
         *  1、将person中salary归类成一组数据
         *  2、将person中的salary归类成一组数据，并将salary-10
         */
        list.stream().map(x->x.getSalary())
                .forEach(
                        x-> System.out.println(x)
                );
        List<Integer> mapList = list.stream().map(x->x.getSalary()).map(x->x-10).collect(Collectors.toList());
        /**
         *  reduce: 用来计算结果，结果是一个数值
         * 求总和，其中0是基准
         */
        Integer sum = list.stream().map(x->x.getSalary()).reduce(0,(x,y)->x+y);
        int max = list.stream().map(x->x.getSalary()).reduce(0,(x,y)->Integer.max(x,y));
        /**
         * collect : 将处理后的结果输出到新的集合中,如list，set，map等 ;或者返回处理的结果，如求集合的个数，平均值等
         */
       // list.stream().filter().collect(Collectors.toList());
       // list.stream().filter().collect(Collectors.toSet());
        Long count = list.stream().filter(p->p.getGender()==0).collect(Collectors.counting());
        Double sumSalary = list.stream().collect(Collectors.summingDouble(x->x.getSalary()));

    }

    /**
     * 去重
     */
    @Test
    public void test2() {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,10));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,40));
            add(new Person2("三木","sanmu",0,50));
        }};
        // 根据name去重
        List<Person2> unique = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person2::getSalary))), ArrayList::new)
        );
        System.out.println(unique);
    }


    @Test
    public  void testList2Map() {
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,10));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,40));
            add(new Person2("三木","sanmu",0,50));
        }};
        //将list转换map
        Map<String, Person2> childInfoMap = list.stream().collect(
                Collectors.toMap(c -> c.getName(), c -> c,(c1, c2) -> c1)
        );
        System.out.println(childInfoMap);

    }

    @Test
    public  void List2List(){
        List<Person2> list = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,10));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,40));
            add(new Person2("三木","sanmu",0,50));
        }};
        List<Integer> orderNoList = list.stream()
                .map(Person2::getSalary)
                .distinct() //不加这个不会去重复
                .collect(Collectors.toList());
        System.out.println(orderNoList);
    }

    @Test
    public  void List2List2(){
        LinkedHashMap<String,String> content = new LinkedHashMap<>(4);
        content.put("授权企业：", "dddd");
        content.put("授权应用：","1");
        content.put("描述","企微授权企业获取授权码失败(未知)" );
        StringBuilder stringBuilder = new StringBuilder();

        if (CollectionUtil.isNotEmpty(content)) {
            //Set<String> keySet = content.keySet();
            System.out.println(content.size());
            int index = 0;
            for(String key : content.keySet()){
                //System.out.println(key.in);
                index++;
                System.out.println("index="+index);
                stringBuilder.append("\t").append(key).append(": \t").append(content.get(key));
                if(index != content.size()){
                    stringBuilder.append("\n");
                }
            }
            /*content.forEach((k, v) -> {
                stringBuilder.append("\t").append(k).append(": \t").append(v).append("\n");
                System.out.println(k.intern());
            });*/
        }
        System.out.println(stringBuilder.toString());
    }


}
