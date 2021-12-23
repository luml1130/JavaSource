package com.luml.java.collection.list;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description  集合交集
 * @date 2020/10/12
 */
public class oper1 {

    public static void main(String[] args) {
        //addHead();
        List<Long> A = new ArrayList<Long>();
        A.add(47908L);

        List<Long> B = new ArrayList<Long>();
        B.add(47905L);

        Collection C = new ArrayList<Long>(A);
        C.retainAll(B);
        if(C.size() >  0){
            System.out.println("A 与 B 并集：");
        }
    }



    /**
     * retainAll:
     * 可以看到这个方法改变了集合A中的元素，将存在于集合A中但不存在于集合B中的元素移除。
     * 如果集合A的大小发生了改变，返回true，即使两个集合完全没有交集，也会返回true。
     * 如果集合A的大小没有发生改变，返回false，即使两个集合完全相同，也会返回false。
     * 所以，retainAll的返回值并不能用于判断两个集合是否存在交集，只能用于判断集合大小是否发生改变；
     * 应该通过集合的大小判断两个集合是否有交集。
     */
    @Test
    public void test() {
        List<Integer> A = new ArrayList<Integer>();
        A.add(1);
        A.add(2);
        A.add(3);
        A.add(4);

        List<Integer> B = new ArrayList<Integer>();
        B.add(2);
        B.add(4);
        B.add(5);
        B.add(6);

        /**
         * 使用retainAll会改变list1的值，所以写一个替代
         * 交集
         */
        Collection C = new ArrayList<Integer>(A);
        C.retainAll(B);
        System.out.println("A 与 B 并集：" + C);
        if(C.size() >  0){
            System.out.println("A 与 B 有并集：");
        }

        B.removeAll(C);
        System.out.println("A 关于 B 的相对补集：" + B);

        A.removeAll(C);
        System.out.println("B 关于 A 的相对补集：" + A);
        /**
         * A 与 B 并集：[2, 4]
         * A 关于 B 的相对补集：[5, 6]
         * B 关于 A 的相对补集：[1, 3]
         */

    }

    @Test
    public  void Test18(){
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item))
                .collect(Collectors.toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out :: println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(Collectors.toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out :: println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(Collectors.toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out :: println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(Collectors.toList());
        List<String> listAll2 = list2.parallelStream().collect(Collectors.toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out :: println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(Collectors.toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out :: println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out :: println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out :: println);
    }

    public static void addHead(){
        List<String> strList1 = new ArrayList<>();
        strList1.add("d");
        strList1.add("e");
        strList1.add("f");
        strList1.add("g");
        System.out.println(strList1.toString());

        List<String> strList2 = new ArrayList<>();
        strList2.add("a");
        strList2.add("b");
        strList2.add("c");
        strList1.addAll(0,strList2);
        System.out.println(strList1.toString());
    }
}
