package com.luml.written.gof;

/**
 * @author luml
 * @description
 *
 * 工厂模式简单实现
 *
 * ‌题目‌：定义一个形状接口 Shape，并通过工厂类根据类型字符串创建具体的形状对象。
 *
 * @date 2026/6/8
 */
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() { System.out.println("Drawing Circle"); }
}

class Square implements Shape {
    public void draw() { System.out.println("Drawing Square"); }
}

public class ShapeFactory {

    public static Shape getShape(String type) {
        if (type == null) return null;
        if ("CIRCLE".equalsIgnoreCase(type)) {
            return new Circle();
        } else if ("SQUARE".equalsIgnoreCase(type)) {
            return new Square();
        }
        return null;
    }

}


