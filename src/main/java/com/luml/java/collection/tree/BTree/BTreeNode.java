package com.luml.java.collection.tree.BTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * 节点结构 (BTreeNode)
 *
 * 每个节点需要存储：
 *
 *     关键字数组（或列表）
 *     子节点指针数组（或列表）
 *     关键字数量
 *     是否为叶子节点的标记
 *     最小度数 tt（通常 m=2tm=2t，即每个节点最少 tt 个子节点，最多 2t2t 个子节点）
 *
 * @date 2026/6/15
 */
public class BTreeNode<T extends Comparable<T>> {

    List<T> keys; // 关键字列表
    List<BTreeNode<T>> children; // 子节点列表
    boolean isLeaf; // 是否为叶子节点
    int t; // 最小度数

    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    // 查找关键字在节点中的位置
    public int findKey(T key) {
        int idx = 0;
        while (idx < keys.size() && keys.get(idx).compareTo(key) < 0) {
            idx++;
        }
        return idx;
    }


}
