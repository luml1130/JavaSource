
package com.ai.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

/**
 * 以下是一个极简的 Java 示例，展示如何使用 LangChain4j 调用本地运行的 Ollama 模型：
 * 代码说明：‌
 *     ‌pom.xml‌：引入了 langchain4j 核心包和 langchain4j-ollama 集成包，
 *                  配置了 Java 17 编译环境和 exec-maven-plugin 以便直接运行。
 *     ‌AiDemoApplication.java‌：
 *         使用 OllamaChatModel.builder() 构建一个指向本地 Ollama 服务（默认端口 11434）的聊天模型实例。
 *         调用 model.generate() 方法发送自然语言提示词。
 *         打印大模型返回的 Java 代码示例及解释。
 *         ‌注意‌：运行前需确保本地已安装 Ollama 并执行 ollama run llama3 下载模型。
 */
public class AiDemoApplication {
    public static void main(String[] args) {
        // 1. 创建 Ollama 模型实例 (假设本地已安装并运行 Ollama，且拉取了 llama3 模型)
        ChatLanguageModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3")
                .build();

        // 2. 发送请求
        String userMessage = "请用 Java 写一个 Hello World 程序，并解释每一行代码。";
        System.out.println("用户提问: " + userMessage);
        System.out.println("-------------------------");

        // 3. 获取响应
        String response = model.generate(userMessage);
        
        // 4. 输出结果
        System.out.println("AI 回答:\n" + response);
    }
}
