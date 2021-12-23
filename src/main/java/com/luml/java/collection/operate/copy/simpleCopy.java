package com.luml.java.collection.operate.copy;

import com.luml.domain.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luml
 * @description:List的复制 （浅拷贝与深拷贝）:https://www.cnblogs.com/luxd/p/11933686.html;
 * @date 2021/8/5 下午11:05
 */
public class simpleCopy {

    @Test
    public void bianLiCopy(){
        List<Person> srcList = new ArrayList<>();
        Person person = new Person();
        person.setAge(1);
        person.setName("22");
        Person person2 = new Person();
        person2.setAge(2);
        person2.setName("33");
        srcList.add(person);
        srcList.add(person2);

        List<Person> destList=new ArrayList<Person>(srcList.size());
        for(Person p : srcList){
            destList.add(p);
        }

        System.out.println(destList.toString());
    }

    /**
     * 2、使用List实现类的构造方法
     * 3、使用List实现类的构造方法
     * 4、使用System.arraycopy()方法
     */
    @Test
    public void Copy(){
        List<Person> srcList = new ArrayList<>();
        Person person = new Person();
        person.setAge(1);
        person.setName("22");
        Person person2 = new Person();
        person2.setAge(2);
        person2.setName("33");
        srcList.add(person);
        srcList.add(person2);

        List<Person> destList=new ArrayList<Person>(srcList);
        System.out.println(destList.toString());

        List<Person> destList2=new ArrayList<Person>();
        destList2.addAll(srcList);
        System.out.println(destList2.toString());

        Person[] srcPersons = srcList.toArray(new Person[0]);
        Person[] destPersons = new Person[srcPersons.length];
        System.arraycopy(srcPersons, 0, destPersons, 0, srcPersons.length);
        System.out.println(Arrays.toString(destPersons));
    }
}
