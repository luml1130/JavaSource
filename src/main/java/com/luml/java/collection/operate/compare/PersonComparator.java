package com.luml.java.collection.operate.compare;

import java.util.Comparator;

/**
 * @author luml
 * @description
 * @date 2020/4/28 16:26
 */
public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        //return 0;
        //使用到了Person类中的compareTo方法进行比较
        //若Person没实现Comparable接口，也可以自行比较
        int ret = p1.getName().compareTo(p2.getName());
        if(ret == 0){
            return p1.getAge().compareTo(p2.getAge());
        }
        return ret;
    }
}
