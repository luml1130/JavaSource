
package com.ai.langchain4j;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;

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
