package com.luml.javase.exception.tryTest;

/**
 * @author luml
 * @description
 * @date 2021/3/3 10:09 上午
 */
public class TryTest3 {
    /**
     * 结果是20 为什么
     * 流程x=10，走到try发现有异常就跳到了catch了x就被改为了20
     * 后面有return 但是这会它有一个执行路径 在寄存器里面会有一个箱子把x的值20给装进去了 但是他没有结束  回头看见finally 但是箱子里已经装了20
     * 程序结束之后20返回
     * @param args
     */
    public static void main(String[] args) {
        Demo d=new Demo();
        int x=d.print();
        System.out.println(x);
    }

}
class Demo{
    public int print(){
        int x=10;
        try{
            System.out.println(10/0);
            return 0;
        }catch(Exception e){
            return 20;
        }finally{
            x=30;
        }
    }
}