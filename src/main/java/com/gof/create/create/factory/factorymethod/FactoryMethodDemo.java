package com.gof.create.create.factory.factorymethod;

/**
 * 工厂方法模式 (Factory Method Pattern) Java 案例
 * 
 * 核心思想：
 *      定义一个创建对象的接口，但让子类决定实例化哪一个类。
 *      工厂方法使一个类的实例化延迟到其子类。
 * 
 * 场景模拟：
 * 假设我们要开发一个日志记录系统，支持多种日志输出方式（如控制台输出、文件输出）。
 * 使用工厂方法模式，我们可以轻松扩展新的日志类型，而无需修改现有代码（符合开闭原则）。
 *
 * 该代码实现了标准的工厂方法模式。
 * 主要特点包括：定义了Logger接口作为抽象产品，ConsoleLogger等作为具体产品；
 *             定义了LoggerFactory接口作为抽象工厂，各具体工厂负责实例化对应的日志对象。
 * 这种结构使得系统在增加新的日志类型（如EmailLogger）时，只需添加新的类和工厂，完全符合开闭原则，避免了修改现有逻辑带来的风险。
 */

// 1. 抽象产品角色 (Product)
// 定义日志记录器的公共接口
interface Logger {
    void log(String message);
}

// 2. 具体产品角色 (Concrete Product)
// 控制台日志记录器
class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[Console Log]: " + message);
    }
}

// 文件日志记录器
class FileLogger implements Logger {
    @Override
    public void log(String message) {
        // 模拟写入文件操作
        System.out.println("[File Log]: Writing to file -> " + message);
    }
}

// 数据库日志记录器 (用于展示扩展性)
class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        // 模拟写入数据库操作
        System.out.println("[Database Log]: Saving to DB -> " + message);
    }
}

// 3. 抽象工厂角色 (Creator)
// 定义创建 Logger 对象的工厂接口
interface LoggerFactory {
    Logger createLogger();
}

// 4. 具体工厂角色 (Concrete Creator)
// 控制台日志工厂
class ConsoleLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        return new ConsoleLogger();
    }
}

// 文件日志工厂
class FileLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}

// 数据库日志工厂
class DatabaseLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        return new DatabaseLogger();
    }
}

// 5. 客户端测试类
public class FactoryMethodDemo {
    public static void main(String[] args) {
        System.out.println("=== 工厂方法模式演示 ===");

        // 场景1: 使用控制台日志
        LoggerFactory consoleFactory = new ConsoleLoggerFactory();
        Logger consoleLogger = consoleFactory.createLogger();
        consoleLogger.log("系统启动成功");

        System.out.println("-------------------------");

        // 场景2: 使用文件日志
        LoggerFactory fileFactory = new FileLoggerFactory();
        Logger fileLogger = fileFactory.createLogger();
        fileLogger.log("用户登录异常: ID=1001");

        System.out.println("-------------------------");

        // 场景3: 使用数据库日志 (展示扩展性，无需修改原有工厂代码)
        LoggerFactory dbFactory = new DatabaseLoggerFactory();
        Logger dbLogger = dbFactory.createLogger();
        dbLogger.log("订单支付完成: OrderID=2023001");
        
        System.out.println("\n=== 优势说明 ===");
        System.out.println("1. 符合开闭原则：新增日志类型只需新增具体产品和具体工厂，无需修改现有代码。");
        System.out.println("2. 解耦：客户端只依赖抽象工厂和抽象产品，不依赖具体实现类。");
        System.out.println("3. 灵活性：可以在运行时通过配置决定使用哪种工厂。");
    }
}
