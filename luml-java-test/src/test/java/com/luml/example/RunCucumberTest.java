package com.luml.example;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;


@Suite // 指定这是一个测试套件
@IncludeEngines("cucumber")// 指定使用Cucumber引擎
@SelectClasspathResource("features") // 指定特征文件所在的目录
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.luml.example.feature.steps")
    // 指定步骤定义类所在的包
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html")
    // 指定报告输出格式和路径
public class RunCucumberTest {
    // 这个类作为JUnit 5的入口点，用于触发Cucumber测试执行
    // 无需编写任何方法体
}
