package com.luml.written;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import javax.xml.transform.Source;

/**
 * @author luml
 * @description
 * @date 2026/6/8
 */
public class nodeTest {

    /**
     * 1. 反转单链表
     *
     * ‌题目‌：给定一个单链表的头节点 head，请反转该链表并返回新的头节点。
     * ‌思路‌：使用三个指针 prev、curr、next 迭代遍历，逐个改变节点的指向。
     */
    @Test
    public  void reverseList() {
    //public  ListNode reverseList(ListNode head) {
        ListNode head = null; // TODO new ListNode();

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next; // 暂存下一个节点
            curr.next = prev;              // 反转指向
            prev = curr;                   // prev 前移
            curr = nextTemp;               // curr 前移
        }
        // prev 成为新的头节点
        //return prev;
        System.out.println(prev);
    }

    /**
     * 2、判断二叉树是否对称
     *
     * ‌题目‌：给定一个二叉树，检查它是否是镜像对称的。
     * ‌思路‌：递归比较左子树的左节点与右子树的右节点，以及左子树的右节点与右子树的左节点。
     */
    @Test
    public  void duichengList() {
        TreeNode test = new TreeNode(3);
        isSymmetric(test);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return (left.val == right.val)
                && check(left.left, right.right)
                && check(left.right, right.left);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


