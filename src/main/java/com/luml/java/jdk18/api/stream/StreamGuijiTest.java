package com.luml.java.jdk18.api.stream;

import com.luml.domain.Person2;
import com.luml.java.data.enumT.DepartmentTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * 归集操作-最终操作
 *      toList()：收集为List（允许重复元素）。
 *      toSet()：收集为Set（自动去重）。
 *      toMap()：收集为Map，需指定键值映射函数。
 *
 * @date 2025/12/5
 */
public class StreamGuijiTest {

    //toMap
    public static void main(String[] args) {
        Map<Integer, String> BID_STATUS_MAP = Arrays.stream(DepartmentTypeEnum.values())
                .collect(Collectors.toMap(DepartmentTypeEnum::getCode, DepartmentTypeEnum::getName));

        System.out.println(BID_STATUS_MAP);
        //{1=班级, 2=年级, 3=学段, 4=校区, 5=学校}

        // return baseGoodsInfoList.stream().collect(Collectors.toMap(BaseGoodsInfo::getGoodsName, BaseGoodsInfo::getId));

    }

    //toList
    public static void main2(String[] args) {
        List<Person2> person2List = new ArrayList<Person2>(){{
            add(new Person2("张三","zhangsan",0,10));
            add(new Person2("李四","zhangsan",1,20));
            add(new Person2("王五","wangwu",0,30));
            add(new Person2("小刘","xiaoliu",1,30));
            add(new Person2("三木","sanmu",0,50));
        }};

        List<Integer> salaryList =
                person2List.stream().map(Person2::getSalary).collect(Collectors.toList());

        System.out.println(salaryList);//[10, 20, 30, 30, 50]

    }
}
