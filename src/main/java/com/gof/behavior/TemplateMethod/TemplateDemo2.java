package com.gof.behavior.TemplateMethod;

/**
 * 模板方法模式简洁示例：模拟数据解析流程
 */
abstract class DataParser {

    // 1. 模板方法：定义算法骨架，使用 final 防止子类修改流程
    public final void parse(String data) {
        System.out.println("--- 开始解析 ---");
        readData(data);      // 具体步骤1：读取（由子类实现）
        processData();       // 具体步骤2：处理（公共逻辑）
        saveResult();        // 具体步骤3：保存（由子类实现）
        System.out.println("--- 解析结束 ---\n");
    }

    // 抽象方法：强制子类实现
    protected abstract void readData(String data);
    protected abstract void saveResult();

    // 公共方法：所有子类共享的逻辑
    private void processData() {
        System.out.println("正在清洗和标准化数据...");
    }
}

// 具体子类：JSON 解析器
class JsonParser extends DataParser {
    @Override
    protected void readData(String data) {
        System.out.println("[JSON] 解析字符串: " + data);
    }

    @Override
    protected void saveResult() {
        System.out.println("[JSON] 存入 MongoDB");
    }
}

// 具体子类：XML 解析器
class XmlParser extends DataParser {
    @Override
    protected void readData(String data) {
        System.out.println("[XML] 解析 DOM 树: " + data);
    }

    @Override
    protected void saveResult() {
        System.out.println("[XML] 存入 Oracle 数据库");
    }
}

// 客户端测试
public class TemplateDemo2 {
    public static void main(String[] args) {
        DataParser jsonParser = new JsonParser();
        jsonParser.parse("{\"id\": 1}");

        DataParser xmlParser = new XmlParser();
        xmlParser.parse("<root><id>1</id></root>");
    }
}
