package com.gof._1_create.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/7/7
 */
public class Email {
    private final String to;      // 必填
    private final String subject; // 必填
    private final String body;    // 必填
    private final List<String> attachments; // 选填

    // 私有构造，强制通过 Builder 创建
    private Email(Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.body = builder.body;
        this.attachments = builder.attachments;
    }

    public static class Builder {
        private final String to;
        private final String subject;
        private final String body;
        private List<String> attachments = new ArrayList<>();

        // 必填参数在构造器中传入
        public Builder(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }

        // 选填参数通过方法设置，返回 this 支持链式调用
        public Builder addAttachment(String attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }

    @Override
    public String toString() {
        return "Email{to='" + to + "', subject='" + subject + "', attachments=" + attachments + "}";
    }
}

// 使用
class Main2 {
    public static void main(String[] args) {
        Email email = new Email.Builder("user@example.com", "Hello", "Body content")
                .addAttachment("file1.pdf")
                .addAttachment("image.png")
                .build();
        System.out.println(email);
    }
}
