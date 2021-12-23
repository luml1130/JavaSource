package com.luml.java.keyword;


import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2020/8/25
 */
//@Slf4j
public class SwitchTest {
    int result = 0;
    int[] sw = new int[]{3,6,7,8,10,16,18,44};
    @Test
    public void testDefault(){
        Long start = System.currentTimeMillis();
        for(int i=0;i<10000000;i++){
            //调用包含switch语句的函数
            //result = switchInt(i);
            result = arrayInt(sw,i);
        }
        System.out.println("耗时：{"+(System.currentTimeMillis()-start)+"}");
        //log.info();
    }


    /**
     * 根据操作数不同，返回不同的值
     * @param arg 参数
     * @return
     */
    protected int switchInt(int arg){
        int index = arg%10+1;
        switch (index){
            case 1: return 3;
            case 2: return 6;
            case 3: return 7;
            case 4: return 8;
            case 5: return 10;
            case 6: return 16;
            case 7: return 18;
            case 8: return 44;
            default: return -1;
        }
    }

    /**
     * 使用数组替换switch
     * @param sw 数组
     * @param arg
     * @return
     */
    protected int arrayInt(int[] sw,int arg) {
        int index = arg % 10 + 1;
        if (index > 7 || index < 1) {
            return -1;
        } else {
            return sw[index];
        }
    }
}
