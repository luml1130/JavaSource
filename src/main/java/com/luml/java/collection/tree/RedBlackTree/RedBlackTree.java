package com.luml.java.collection.tree.RedBlackTree;

/**
 * @author luml
 * @description
 * @date 2026/6/14
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    // 颜色常量
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // 节点内部类
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        Node parent;
        boolean color; // true for RED, false for BLACK

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private Node root;

    public RedBlackTree() {
        this.root = null;
    }

    /**
     * 基础辅助方法：旋转
     *
     * 旋转是维持红黑树平衡的核心操作。
     *
     *     ‌左旋‌：将右子节点提升为父节点，原父节点变为左子节点。
     *     ‌右旋‌：将左子节点提升为父节点，原父节点变为右子节点。
     *
     * @param node
     */

    // 左旋：以 node 为支点进行左旋
    private void leftRotate(Node node) {
        Node rightChild = node.right;

        // 1. 将 rightChild 的左子树挂载到 node 的右子树
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        // 2. 将 rightChild 提升到 node 的位置
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild; // 如果 node 是根节点，更新根
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        // 3. 将 node 变为 rightChild 的左子节点
        rightChild.left = node;
        node.parent = rightChild;
    }



    // 右旋：以 node 为支点进行右旋
    private void rightRotate(Node node) {
        Node leftChild = node.left;

        // 1. 将 leftChild 的右子树挂载到 node 的左子树
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        // 2. 将 leftChild 提升到 node 的位置
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild; // 如果 node 是根节点，更新根
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        // 3. 将 node 变为 leftChild 的右子节点
        leftChild.right = node;
        node.parent = leftChild;
    }


    /**
     * 3. 插入操作与平衡修复
     *
     * 插入新节点时，默认将其颜色设为‌红色‌（因为设为黑色会破坏“黑色高度相同”的性质，修复起来更复杂）。插入后，如果父节点也是红色，则违反了“不能有连续红色节点”的规则，需要进行修复。
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Node newNode = new Node(key, value, RED);

        // 1. 标准 BST 插入逻辑
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = null;
            while (current != null) {
                parent = current;
                int cmp = key.compareTo(current.key);
                if (cmp < 0) {
                    current = current.left;
                } else if (cmp > 0) {
                    current = current.right;
                } else {
                    current.value = value; // 更新已存在的键
                    return;
                }
            }
            newNode.parent = parent;
            if (key.compareTo(parent.key) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        // 2. 修复红黑树性质
        fixInsert(newNode);
    }

    private void fixInsert(Node node) {
        Node parent, grandParent;

        // 只要父节点是红色，就需要修复（根节点必须是黑色，所以循环条件隐含了 node 不是根）
        while (node != root && node.parent.color == RED) {
            parent = node.parent;
            grandParent = parent.parent;

            // 情况 A: 父节点是祖父节点的左孩子
            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                // Case 1: 叔叔节点是红色 -> 变色
                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent; // 继续向上检查祖父节点
                }
                // Case 2 & 3: 叔叔节点是黑色或不存在
                else {
                    // Case 2: 当前节点是父节点的右孩子 -> 先左旋变成 Case 3
                    if (node == parent.right) {
                        leftRotate(parent);
                        node = parent; // 更新 node 指向原来的父节点
                        parent = node.parent; // 更新 parent
                    }
                    // Case 3: 当前节点是父节点的左孩子 -> 右旋 + 变色
                    parent.color = BLACK;
                    grandParent.color = RED;
                    rightRotate(grandParent);
                }
            }
            // 情况 B: 父节点是祖父节点的右孩子 (对称逻辑)
            else {
                Node uncle = grandParent.left;

                // Case 1: 叔叔节点是红色 -> 变色
                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandParent.color = RED;
                    node = grandParent;
                }
                // Case 2 & 3: 叔叔节点是黑色或不存在
                else {
                    // Case 2: 当前节点是父节点的左孩子 -> 先右旋变成 Case 3
                    if (node == parent.left) {
                        rightRotate(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    // Case 3: 当前节点是父节点的右孩子 -> 左旋 + 变色
                    parent.color = BLACK;
                    grandParent.color = RED;
                    leftRotate(grandParent);
                }
            }
        }

        // 确保根节点始终是黑色
        root.color = BLACK;
    }

    /**
     * 4、查找操作
     *
     * 红黑树的查找与普通二叉搜索树完全一致，时间复杂度为 O(log⁡N)O(logN)。
     * @param key
     * @return
     */
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    /**
     * 5、验证工具（可选）
     *
     * 为了确保实现的正确性，可以添加一个简单的验证方法，检查是否满足红黑树的五大性质。
     */

    public boolean isRedBlackTree() {
        if (root == null) return true;
        // 性质2: 根节点必须是黑色
        if (root.color != BLACK) return false;

        // 性质4 & 5: 递归检查
        return isValidNode(root);
    }

    private boolean isValidNode(Node node) {
        if (node == null) return true;

        // 性质4: 红色节点的子节点必须是黑色
        if (node.color == RED) {
            if (node.left != null && node.left.color == RED) return false;
            if (node.right != null && node.right.color == RED) return false;
        }

        return isValidNode(node.left) && isValidNode(node.right);
    }



}
