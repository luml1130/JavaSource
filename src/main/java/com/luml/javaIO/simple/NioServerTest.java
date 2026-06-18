package com.luml.javaIO.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author luml
 * @description
 *  NIO 服务端
 *  测试：
 *      依次启动服务端、客户端，看看控制台输出情况如何。
 *
 *      服务端控制台结果如下：
 *          接收到新的客户端连接，地址：/127.0.0.1:57644
 *          收到客户端发送的信息，内容：Hello，我是客户端
 *
 *      客户端控制台结果如下：
 *          客户端收到服务端：/127.0.0.1:8080，返回的信息：server send
 *
 *  从编程上可以看到，NIO 的操作比传统的 IO 操作要复杂的多！
 * @date 2026/6/18
 */
public class NioServerTest {
    public static void main(String[] args) throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        ssc.configureBlocking(false);
        // 进行服务的绑定，监听8080端口
        ssc.socket().bind(new InetSocketAddress(8080));

        // 构建一个Selector选择器,并且将channel注册上去
        Selector selector = Selector.open();
        // 将serverSocketChannel注册到selector,并对accept事件感兴趣(serverSocketChannel只能支持accept操作)
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // 查询指定事件已经就绪的通道数量，select方法有阻塞效果,直到有事件通知才会有返回，如果为0就跳过
            int readyChannels = selector.select();
            if(readyChannels == 0) {
                continue;
            };
            //通过选择器取得所有key集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //判断状态是否有效
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    // 处理通道中的连接事件
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel sc = server.accept();
                    sc.configureBlocking(false);
                    System.out.println("接收到新的客户端连接，地址：" + sc.getRemoteAddress());

                    // 将通道注册到选择器并处理通道中可读事件
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 处理通道中的可读事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while (channel.isOpen() && channel.read(byteBuffer) != -1) {
                        // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                        if (byteBuffer.position() > 0) {
                            break;
                        };
                    }
                    byteBuffer.flip();

                    //获取缓冲中的数据
                    String result = new String(byteBuffer.array(), 0, byteBuffer.limit());
                    System.out.println("收到客户端发送的信息，内容：" + result);

                    // 将通道注册到选择器并处理通道中可写事件
                    channel.register(selector, SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    // 处理通道中的可写事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.put("server send".getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);

                    // 将通道注册到选择器并处理通道中可读事件
                    channel.register(selector, SelectionKey.OP_READ);
                    //写完之后关闭通道
                    channel.close();
                }
                //当前事件已经处理完毕，可以丢弃
                iterator.remove();
            }
        }
    }

}
