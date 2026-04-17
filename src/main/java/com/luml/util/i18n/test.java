package com.luml.util.i18n;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/4/17
 */
public class test {
    public static void main(String[] args) {
        List<Integer> terminalIds = Lists.newArrayList(1,29,21);
        System.out.println(I.n("根据dTid:{0} 查询设备异常", terminalIds));
    }
}
