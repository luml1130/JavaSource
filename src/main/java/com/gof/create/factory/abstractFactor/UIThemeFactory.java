package com.gof.create.factory.abstractFactor;

/**
 * @author luml
 * @description
 * 示例场景（UI主题工厂）
 *      比如我们需要同时支持「暗黑主题」和「浅色主题」两套UI，每套UI都包含按钮、文本框两种组件：
 *      使用时只需要依赖ThemeFactory抽象接口，切换整体主题只需要换一个具体工厂即可，不需要修改业务代码。
 * @date 2026/5/28
 */
// 1. 抽象产品（不同产品等级）
interface Button1 { void render(); }
interface TextBox { void render(); }

// 2. 具体产品
class DarkButton implements Button1 {
    @Override public void render() { System.out.println("渲染暗黑风格按钮"); }
}
class LightButton implements Button1 {
    @Override public void render() { System.out.println("渲染浅色风格按钮"); }
}
class DarkTextBox implements TextBox {
    @Override public void render() { System.out.println("渲染暗黑风格文本框"); }
}
class LightTextBox implements TextBox {
    @Override public void render() { System.out.println("渲染浅色风格文本框"); }
}
// 3. 抽象工厂
interface ThemeFactory {
    Button1 createButton();
    TextBox createTextBox();
}

// 4. 具体工厂（每个具体工厂对应一个产品族）
class DarkThemeFactory implements ThemeFactory {
    @Override public Button1 createButton() { return new DarkButton(); }
    @Override public TextBox createTextBox() { return new DarkTextBox(); }
}
class LightThemeFactory implements ThemeFactory {
    @Override public Button1 createButton() { return new LightButton(); }
    @Override public TextBox createTextBox() { return new LightTextBox(); }
}

public class UIThemeFactory {
}
