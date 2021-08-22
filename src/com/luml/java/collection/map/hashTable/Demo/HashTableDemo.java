package com.luml.java.collection.map.hashTable.Demo;

import java.util.Scanner;

/**
 * @author luml
 * @description：
 * 哈希表的基本原理和实现方法（Java）：https://blog.csdn.net/weixin_44135544/article/details/102766936；
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址…),当输入该员工的id时,要求查找到该员工的 所有信息.
 * 要求:不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
 * 分析：要通过哈希表来存放雇员的信息，则要定义三个类，（雇员类，链表类，哈希表类），然后再雇员类中定义相应的查找、增加、遍历方法。
 * @date 2020/5/9 19:05
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashtab = new HashTable(7);
        String key = "";
        Scanner sr = new Scanner(System.in);
        while (true) {
            System.out.println("add:增加雇员，list：显示雇员，find:查找雇员，exit：退出");
            key = sr.next();
            switch (key) {
                case "add":
                    System.out.println("请输入雇员id：");
                    int id = sr.nextInt();
                    System.out.println("请输入雇员name：");
                    String name = sr.next();
                    Emp emp = new Emp(id, name);
                    hashtab.add(emp);
                    break;
                case "list":
                    hashtab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找雇员的id：");
                    int findId = sr.nextInt();
                    hashtab.findEmpById(findId);
                    break;
                case "exit":
                    sr.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
