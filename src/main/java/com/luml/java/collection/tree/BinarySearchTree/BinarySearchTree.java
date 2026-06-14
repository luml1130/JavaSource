package com.luml.java.collection.tree.BinarySearchTree;

/**
 * @author luml
 * @description 节点定义
 * @date 2026/6/14
 */
public class BinarySearchTree {
    // 静态内部类：定义树节点
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root; // 根节点

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * 查找操作 (Search)
     * 查找过程类似于二分查找：
     *     若目标值等于当前节点值，返回该节点。
     *     若目标值小于当前节点值，在左子树继续查找。
     *     若目标值大于当前节点值，在右子树继续查找。
     * @param key
     * @return
     */
    public TreeNode search(int key) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == key) {
                return cur; // 找到目标
            } else if (cur.val < key) {
                cur = cur.right; // 往右找
            } else {
                cur = cur.left;  // 往左找
            }
        }
        return null; // 未找到
    }

    /**
     * 插入操作 (Insert)
     *
     * 插入前需先查找位置，确保不破坏 BST 性质。如果树为空，直接创建根节点；否则找到合适的叶子节点位置进行插入。
     * @param key
     */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode parent = null;
        TreeNode cur = root;

        // 寻找插入位置
        while (cur != null) {
            if (cur.val < key) {
                parent = cur;
                cur = cur.right;
            } else if (cur.val > key) {
                parent = cur;
                cur = cur.left;
            } else {
                return; // 键值已存在，不插入重复元素
            }
        }

        // 执行插入
        TreeNode newNode = new TreeNode(key);
        if (parent.val < key) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }


    /**
     * 删除操作 (Remove)
     *
     * 删除操作较为复杂，分为三种情况：
     *
     *     ‌待删除节点无左子树‌：直接用其右子树替换该节点。
     *     ‌待删除节点无右子树‌：直接用其左子树替换该节点。
     *     ‌待删除节点左右子树均存在‌：
     *         找到右子树中的最小节点（即右子树的最左节点，称为后继节点）。
     *         用后继节点的值替换待删除节点的值。
     *         删除那个后继节点（此时后继节点一定没有左子树，转化为情况1或2处理）。
     * @param key
     */
    public void remove(int key) {
        if (root == null) return;

        TreeNode parent = null;
        TreeNode cur = root;

        // 1. 查找待删除节点
        while (cur != null) {
            if (cur.val == key) break;
            else if (cur.val < key) {
                parent = cur;
                cur = cur.right;
            } else {
                parent = cur;
                cur = cur.left;
            }
        }

        if (cur == null) return; // 未找到该节点

        // 2. 执行删除逻辑
        // 情况1: 左子树为空
        if (cur.left == null) {
            if (cur == root) {
                root = cur.right;
            } else if (cur == parent.left) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        }
        // 情况2: 右子树为空
        else if (cur.right == null) {
            if (cur == root) {
                root = cur.left;
            } else if (cur == parent.left) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        }
        // 情况3: 左右子树都不为空
        else {
            // 找到右子树的最小节点（后继节点）
            TreeNode targetParent = cur;
            TreeNode target = cur.right;
            while (target.left != null) {
                targetParent = target;
                target = target.left;
            }

            // 替换值
            cur.val = target.val;

            // 删除后继节点
            if (target == targetParent.left) {
                targetParent.left = target.right;
            } else {
                targetParent.right = target.right;
            }
        }
    }



}
