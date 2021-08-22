package com.luml.java.collection.operate.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author luml
 * @description:
 *      https://www.jianshu.com/p/3dbbbca41f7c 有道：F-功能
 * @date 2020/4/28 16:18
 */
public class TestComparable {

    public static void main(String[] args) {
        compareUser();
    }

    public static void compareUser() {
        //取数据
        List<User> userList = generateUserList();
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User d1, User d2) {
                int diff =  d1.getAge() - d2.getAge();
                if(diff > 0 ){
                    return 1;
                }else if( diff < 0 ){
                    return  -1;
                }
                return 0;
            }
        });
       for(User user : userList){
           System.out.println(user.getAge());
       }

    }

    public static void comparePerson() {
        //取数据
        List<Person> personList = generatePersonList();
        //排序一: 正常 这个并未指定比较器,是用的类本身的比较器
        Collections.sort(personList);
        print("Collections.sort(list)",personList);

        //排序二：发现正常了
        PersonComparator pc = new PersonComparator();
        //PersonComparator2 pc2 = new PersonComparator2();
       // Collections.sort(List<T> list, Comparator<? super T> c);
        Collections.sort(personList,pc.thenComparing(pc));  // 这里是先按照pc排序，再按照pc2 排序
        print("thenComparing",personList);

        //排序三：正常
        SortedSet<Person> ts = new TreeSet<>(personList);
        print("TreeSet",ts);

        //排序四：发现某个Person实例被吃掉了,因为TreeSet认为CompareTo为0，则两个Person实例相同
        SortedSet<Person> ts2 = new TreeSet<>(new PersonComparator());
        ts2.addAll(personList);
        print("TreeSet(Comparator)",ts2);

        List<Integer> list = Arrays.asList(1, 2, 1,3,5,3);
        SortedSet<Integer> it = new TreeSet<>(list);
        System.out.println(it);
    }
    //生成待排序数组
    private static List<Person> generatePersonList() {
        List<Person> retList = new ArrayList<>(16);
        retList.addAll(Arrays.asList(new Person("erMaZi", 19, "good"), new Person("liSi", 17, "bad")
                , new Person("wangWu", 18, "middle")
                , new Person("wangWu", 18, "middle")));
        return retList;
    }

    //生成待排序数组
    private static List<User> generateUserList() {
        List<User> retList = new ArrayList<>(16);
        retList.addAll(Arrays.asList(new User("erMaZi", 19, "good"), new User("liSi", 17, "bad")
                , new User("wangWu", 18, "middle")
                , new User("wangWu", 18, "middle")));
        return retList;
    }


    public static void print(String message,List<Person> personList){
        System.out.println(message+":");
        for(Person p:personList){
            System.out.println(p);
        }
        System.out.println();
    }

    public static void print(String message, SortedSet<Person> sortedSet){
        System.out.println(message+":");
        for(Person p:sortedSet){
            System.out.println(p);
        }
        System.out.println();
    }
}
