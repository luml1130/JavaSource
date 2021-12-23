package com.luml.thread.daemon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author luml
 * @description
 * @date 2021/4/1 9:33 上午
 */
public class DaemonTest {

    public static void main(String[] args) {
        Runnable tr = new TestRunnable();
        Thread thread = new Thread(tr);
        //设置守护线程
        //thread.setDaemon(true);
        //开始执行分进程
        thread.start();
    }
    /**
     * （这个是错误的例子不能将业务代码写到守护线程里面）
     * 运行结果：文件daemon.txt中没有"ddda"字符串。
     * 把输入输出逻辑包装进守护线程多么的可怕，字符串并没有写入指定文件。原因也很简单，直到主线程完成，守护线程仍处于1秒的阻塞状态。
     *    这个时候主线程很快就运行完了，虚拟机退出，Daemon停止服务，输出操作自然失败了.　　
     * 但是如果把thread.setDaemon(true); //设置守护线程注释掉，文件daemon.txt是可以被写入daemon字符串
     */
}

class TestRunnable implements Runnable{
    @Override
    public void run(){
        try{
            //守护线程阻塞1秒后运行
            Thread.sleep(1000);
            File f = new File("/file/daemon.txt");
            FileOutputStream os=new FileOutputStream(f,true);
            os.write("daemon".getBytes());
        }catch(IOException e1){
            e1.printStackTrace();
        }catch(InterruptedException e2){
            e2.printStackTrace();
        }
    }
}
