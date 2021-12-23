package com.luml.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description:堆OOM 例子
 *  类的vm options：设置为：VM -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *          前面不加vm
 * @date 2020/8/31
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<HeapOOM>();
        while(true){
            list.add(new HeapOOM());
        }
    }
}
