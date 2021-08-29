package com.luml.java.exception.tryTest;

/**
 * @author luml
 * @description
 * @date 2021/3/3 9:47 上午
 */
public class Try_Catch_Finally_Test {

    public static void main(String[] args) {
        /*
         * try{}里有一个return语句，那么紧跟在这个try后的finally {}里的code会不会被执行，什么时候被执行，在return前还是后?
         */
        //System.out.println("执行return先不返回值，把值暂存，等待finally执行完再把那个保存的值返回 " + test());

        System.out.println("执行return先不返回值，把值暂存，等待finally执行完再把那个保存的值返回 " + test2());
    }

    static int test() {
        int x = 1;
        try {
            return ++x;
        } finally {
            ++x;
            System.out.println("finally先执行，return最后执行 "+x);
        }
    }

    /**finally中也有return ，最终返回finally中的return**/
    static int test2() {
        int x = 1;
        try {
            return ++x;
        }catch(Exception e) {
            e.printStackTrace();
            return ++x;
        }finally {
            ++x;
            System.out.println("finally先执行，return最后执行 "+x);
            return ++x;
        }
    }
}
