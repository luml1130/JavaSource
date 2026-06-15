package com.luml.java.collection.tree.BTree;

/**
 * @author luml
 * @description
 * @date 2026/6/15
 */
public class BTree<T extends Comparable<T>> {
    private BTreeNode<T> root;
    private int t; // 最小度数

    public BTree(int t) {
        this.t = t;
        this.root = null;
    }

    // 搜索关键字
    public BTreeNode<T> search(T key) {
        if (root == null) return null;
        return search(root, key);
    }

    private BTreeNode<T> search(BTreeNode<T> node, T key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i++;
        }

        // 如果找到关键字
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            return node;
        }

        // 如果是叶子节点，未找到
        if (node.isLeaf) {
            return null;
        }

        // 递归搜索子节点
        return search(node.children.get(i), key);
    }

    // 插入关键字
    public void insert(T key) {
        if (root == null) {
            root = new BTreeNode<>(t, true);
            root.keys.add(key);
        } else {
            // 如果根节点已满，需要分裂
            if (root.keys.size() == 2 * t - 1) {
                BTreeNode<T> newRoot = new BTreeNode<>(t, false);
                newRoot.children.add(root);
                splitChild(newRoot, 0, root);

                // 确定新key应该插入哪个子节点
                int i = 0;
                if (key.compareTo(newRoot.keys.get(0)) > 0) {
                    i++;
                }
                insertNonFull(newRoot.children.get(i), key);
                root = newRoot;
            } else {
                insertNonFull(root, key);
            }
        }
    }

    // 向非满节点插入关键字
    private void insertNonFull(BTreeNode<T> node, T key) {
        int i = node.keys.size() - 1;

        if (node.isLeaf) {
            // 叶子节点，直接插入并排序
            node.keys.add(null); // 占位
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, key);
        } else {
            // 非叶子节点，找到合适的子节点
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i--;
            }
            i++;

            // 如果子节点已满，先分裂
            if (node.children.get(i).keys.size() == 2 * t - 1) {
                splitChild(node, i, node.children.get(i));
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key);
        }
    }

    // 分裂子节点
    private void splitChild(BTreeNode<T> parent, int index, BTreeNode<T> child) {
        BTreeNode<T> newNode = new BTreeNode<>(child.t, child.isLeaf);

        // 将child的后半部分关键字移到newNode
        for (int j = 0; j < t - 1; j++) {
            newNode.keys.add(child.keys.remove(t));
        }

        // 如果child不是叶子，移动子节点指针
        if (!child.isLeaf) {
            for (int j = 0; j < t; j++) {
                newNode.children.add(child.children.remove(t));
            }
        }

        // 将child的中间关键字提升到parent
        parent.keys.add(index, child.keys.remove(t - 1));
        parent.children.add(index + 1, newNode);
    }
}
