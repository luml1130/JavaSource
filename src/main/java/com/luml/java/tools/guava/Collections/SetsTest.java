package com.luml.java.tools.guava.Collections;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2026/5/4
 */
public class SetsTest {

    /**
     * 在 Google Guava 库中，Sets.immutableCopy 并不是一个直接存在的静态方法，而是通过 ‌SetView 对象的 immutableCopy() 方法‌ 实现的。
     * 该方法用于将一个集合视图（如交集、差集等）转换为‌不可变的副本‌。
     * 核心要点
     *     ‌Sets.immutableCopy 本身不是 Guava 的公开 API 方法‌，但 SetView 类提供了 immutableCopy() 方法。
     *     SetView 是由 Sets.intersection()、Sets.difference()、Sets.symmetricDifference() 等方法返回的集合视图类型。
     *     调用 immutableCopy() 会返回一个 ‌不可变的 ImmutableSet‌，其内容为当前视图所代表的元素集合。
     */
    @Test
    public void ImmutableSetTest(){
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 7, 8, 9);
        Set<Integer> set2 = Sets.newHashSet(2, 3, 6, 7, 10);

        // 获取交集视图
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        // 转换为不可变副本
        ImmutableSet<Integer> immutableIntersection = intersection.immutableCopy();

        System.out.println(immutableIntersection); // 输出: [2, 3, 7]

    }

    /**
     *Sets.intersection 是 ‌Google Guava 库‌中用于计算两个或多个集合交集的静态方法，返回一个‌不可修改的视图‌，包含所有集合中共有的元素。
     * 核心特性
     *     ‌返回类型‌：SetView<E>（不可修改的视图）
     *     ‌顺序保证‌：迭代顺序与‌第一个集合‌（即较小集合）的迭代顺序一致
     *     ‌性能优化‌：基于较小集合进行遍历，效率更高
     *     ‌动态更新‌：视图会随原始集合的变化而自动更新
     */
    @Test
    public void SetsTest(){
        /*Set<String> oldTerminalIdList = oldTerminalList.stream().map(WaybillTerminalDTO::getTerminalId).collect(Collectors.toSet());
        Set<String> newTerminalIdList = terminalListDTOList.stream().map(TerminalListDTO::getTerminalId).collect(Collectors.toSet());
        Sets.SetView<String> intersection = Sets.intersection(oldTerminalIdList, newTerminalIdList);
        ImmutableSet<String> intersectionSet = intersection.immutableCopy();
        if (CollectionUtils.isNotEmpty(intersectionSet)) {
            List<String> existNo = intersectionSet.stream().map(idToNoMap::get).filter(StringUtils::isNotBlank).collect(Collectors.toList());
            throw new E6BusinessException(I.n("设备{0}已绑定至当前运单", StringUtils.joinWith(",", existNo)));
        }*/

        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(4, 5, 6, 7, 8);

        //计算两个或多个集合交集的静态方法
        Set<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection); // 输出: [4, 5]（顺序可能不同）

    }
}
