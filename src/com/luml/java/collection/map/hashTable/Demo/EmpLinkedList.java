package com.luml.java.collection.map.hashTable.Demo;

/**
 * @author luml
 * @description 链表类
 * @date 2020/5/9 19:07
 */
public class EmpLinkedList {
    private Emp head;// 定义一个头指针指向链表的第一个元素
    // 创建一个方法将emp加入到链表中

    public void add(Emp emp) {
        if (head == null) {// 链表中第一个节点为空
            head = emp;
            return;
        }
        Emp curEmp = head;// 创建一个辅助节点，因为head不能变化
        while (true) {
            if (curEmp.next == null) {// 找到链表的最后
                break;
            }
            curEmp = curEmp.next;// 将指针curEmp后移
        }
        curEmp.next = emp;// 将emp加入链表

    }

    // 遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.printf("链表%d为空\n", no + 1);
            return;
        }
        System.out.printf("第%d条链表信息为：", no + 1);
        Emp curEmp = head;// 辅助指针
        while (true) {
            System.out.printf("==>id=%d name = %s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {// 到链表最后
                break;
            }
            curEmp = curEmp.next;// 后移
        }
        System.out.println();
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {// 找到要查找的节点
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
