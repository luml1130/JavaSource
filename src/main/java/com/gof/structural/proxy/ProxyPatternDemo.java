package com.gof.structural.proxy;

/**
 * @author luml
 * @description：
 *    jdk的静态代理
 * @date 2020/4/21 22:30
 */
public class ProxyPatternDemo {
    /**
     * 当被请求时，使用 ProxyImage 来获取 RealImage 类的对象。
     * @param args
     */
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");
        image.display();  // 图像将从磁盘加载
        System.out.println("");
        image.display();  // 图像不需要从磁盘加载
    }
}

interface Image {
    void display();
}

/**
 * :创建实现接口的实体类。
 */
class RealImage implements Image {
    private String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }
    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}

/**
 * 创建代理类
 */
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    public ProxyImage(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}