package com.luml.java.collection.operate.count;

import com.luml.domain.Person;
import com.luml.domain.User2;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * @author luml
 * @description
 * @date 2025/12/26
 */
public class countTest {

    @Test
    public void testAddAll1(){
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        Set<String> set2 = new HashSet<>();
        set2.add("2");
        set2.add("3");
        set1.addAll(set2);
        System.out.println(set1);

    }

    @Test
    public void testAddAll2(){
        List<Person> srcList = new ArrayList<>();
        Person person = new Person();
        person.setAge(1);
        person.setName("22");
        srcList.add(person);


        List<Person> srcList2 = new ArrayList<>();
        Person person2 = new Person();
        person2.setAge(2);
        person2.setName("33");
        srcList2.add(person2);

        //如果List<Person> srcList = null 会报空指针异常
        //srcList.addAll(srcList2);

        //下面这样也会报空指针异常
        //srcList.addAll(null);
        //下面这样不会报空指针异常
        List<Person> srcList3 = new ArrayList<>();
        srcList.addAll(srcList3);
        System.out.println(srcList);
    }

    @Test
    public void subtractTest(){
        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");

        Set<String> set2 = new HashSet<>();
        set2.add("2");
        set2.add("3");

       Set<String> newSet = (Set<String>) CollectionUtils.subtract(set1, set1);
        //System.out.println(CollectionUtils.subtract(set1, set2));
    }
}
