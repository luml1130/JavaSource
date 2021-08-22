package com.luml.java.collection.map.hashTable.Demo;

/**
 * @author luml
 * @description 哈希表类
 * @date 2020/5/9 19:05
 */
public class HashTable {
    private EmpLinkedList[] empLinkedList;
    private int size;
    public HashTable(int size) {
        this.size = size;
        empLinkedList = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }
    /**
     * 根据员工的id得到应该添加到哪条链表
     * @param emp
     */
    public void add(Emp emp) {
        // 得到emp对应的链表
        int EmpLinkedListNum = hashFun(emp.id);
        empLinkedList[EmpLinkedListNum].add(emp);
    }
    /**
     * 编写散列函数
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }
    /**
     * 遍历hashTab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedList[i].list(i);
        }
    }
    /**
     * 根据id查找在哈希表中对应的链表
     * @param id
     */
    public void findEmpById(int id) {
        int EmpLinkedListNum = hashFun(id);
        Emp emp = empLinkedList[EmpLinkedListNum].findEmpById(id);
        if (emp == null) {
            System.out.println("在链表中为找到该节点\n");
        } else {
            System.out.printf("id为%d的员工在第%d条链表中\n", id, EmpLinkedListNum + 1);
        }
    }
}
