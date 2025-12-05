package com.luml.java.tools.guava;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2025/12/5
 */
public class JoinerTest {
    public static void main(String[] args) {
        List<Integer> idList = new ArrayList<>();
        idList.add(56);
        idList.add(59);
        System.out.println(Joiner.on(",").join(idList));

    }
}
