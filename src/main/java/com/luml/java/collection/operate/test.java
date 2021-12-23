package com.luml.java.collection.operate;

import com.luml.domain.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/8/30 上午12:46
 */
public class test {

    @Test
    public void testAddAll(){
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
}
