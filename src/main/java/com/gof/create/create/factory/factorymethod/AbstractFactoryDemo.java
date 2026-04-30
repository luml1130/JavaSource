package com.gof.create.create.factory.factorymethod;

/**
 * 抽象工厂模式 (Abstract Factory Pattern) Java 案例
 * 
 * 核心思想：
 * 提供一个接口，用于创建相关或依赖对象的家族，而不需要指定它们具体的类。
 * 适用于需要创建一组相关产品（产品族）的场景，如不同操作系统的UI控件、不同风格的家具等。
 * 
 * 场景模拟：
 * 模拟一个跨平台UI框架，需要为 Windows 和 Mac 操作系统分别创建按钮（Button）和复选框（Checkbox）。
 * 每个操作系统对应一个具体工厂，负责创建该风格下的一整套控件。
 *
 * 代码说明：该示例展示了抽象工厂模式在跨平台UI开发中的应用。
 *          定义了Button和Checkbox两个抽象产品接口，以及Windows和Mac两套具体实现。
 *          GUIFactory作为抽象工厂，声明了创建这一族产品的方法。
 *          WindowsFactory和MacFactory作为具体工厂，分别负责实例化对应风格的控件。
 *          Application类依赖于抽象工厂接口，从而实现了与具体产品类的解耦。
 * 这种设计保证了产品族内部的一致性，并使得切换整体风格变得非常简单，只需替换工厂对象即可。
 */

// 1. 抽象产品角色：按钮
interface Button {
    void render();
    void onClick();
}

// 2. 抽象产品角色：复选框
interface Checkbox {
    void render();
    void switchState();
}

// 3. 具体产品：Windows 风格按钮
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("[Windows] 渲染标准 Windows 风格按钮");
    }

    @Override
    public void onClick() {
        System.out.println("[Windows] 按钮被点击，触发 Windows 事件处理");
    }
}

// 4. 具体产品：Mac 风格按钮
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("[Mac] 渲染圆润 Mac 风格按钮");
    }

    @Override
    public void onClick() {
        System.out.println("[Mac] 按钮被点击，触发 Mac 事件处理");
    }
}

// 5. 具体产品：Windows 风格复选框
class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("[Windows] 渲染方形 Windows 复选框");
    }

    @Override
    public void switchState() {
        System.out.println("[Windows] 复选框状态切换：选中/未选中");
    }
}

// 6. 具体产品：Mac 风格复选框
class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("[Mac] 渲染圆形 Mac 复选框");
    }

    @Override
    public void switchState() {
        System.out.println("[Mac] 复选框状态切换：开/关");
    }
}

// 7. 抽象工厂接口：定义创建一族产品的方法
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 8. 具体工厂：Windows 工厂
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// 9. 具体工厂：Mac 工厂
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// 10. 客户端代码：应用类
class Application {
    private Button button;
    private Checkbox checkbox;

    // 构造函数接收抽象工厂，解耦具体实现
    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        System.out.println("--- 开始绘制界面 ---");
        button.render();
        checkbox.render();
        System.out.println("--- 界面绘制完成 ---\n");
        
        // 模拟用户交互
        button.onClick();
        checkbox.switchState();
        System.out.println();
    }
}

// 11. 主程序入口
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== 抽象工厂模式演示 ===\n");

        // 场景1: 在 Windows 环境下运行
        System.out.println("当前环境: Windows");
        GUIFactory windowsFactory = new WindowsFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.paint();

        System.out.println("-------------------------\n");

        // 场景2: 在 Mac 环境下运行
        System.out.println("当前环境: Mac");
        GUIFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        macApp.paint();
        
        System.out.println("=== 优势说明 ===");
        System.out.println("1. 产品族一致性：确保客户端只使用同一系列的产品（如不会混用 Windows 按钮和 Mac 复选框）。");
        System.out.println("2. 易于交换产品系列：只需更换工厂实例（WindowsFactory -> MacFactory），即可切换整个UI风格。");
        System.out.println("3. 符合开闭原则：新增产品族（如 Linux 风格）只需新增具体工厂和产品类，无需修改现有代码。");
    }
}
