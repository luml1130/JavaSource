package com.luml.java.javaclass.date;

/**
 * @author luml
 * @description:
 * date默认精度是毫秒，也就是说生成的时间戳就是13位的，有的时间戳默认就是10位的，因为其精度是秒。
 * 问题2：13位时间戳如何转换成10位时间戳
 *      第一种：通过substring方法，将13位的时间戳最后三位数字截取
 *      第二种：将13位时间戳除以1000取整。
 * @date 2021/8/6 下午11:24
 */
public class Test1 {
}
