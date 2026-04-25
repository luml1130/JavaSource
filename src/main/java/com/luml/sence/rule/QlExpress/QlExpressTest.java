package com.luml.sence.rule.QlExpress;

import com.luml.util.MathUtils;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/4/25
 */
public class QlExpressTest {
    public static void main(String[] args) {
        System.out.println("22");
    }

    /**
     * 调用自定义函数‌
     */
    @Test
    public void test2() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        // 注册函数
        runner.addFunctionOfClassMethod("MAX", MathUtils.class.getName(), "max", new String[]{"int", "int"}, null);
        // 脚本中使用: MAX(a, b)

    }

    /**
     * 条件判断与三元运算‌
     */
    @Test
    public void test() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("score", 85);
        Object res = runner.execute("score >= 90 ? '优秀' : (score >= 60 ? '及格' : '不及格')", context, null, true, false);
        System.out.println(res); // 输出: 及格
    }

    /**
     * 基础表达式计算‌
     */
    @Test
    public void baseTest() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 10);
        context.put("b", 20);

        Object result = runner.execute("a + b * 2", context, null, true, false);
        System.out.println("result="+result); // 输出: 50
    }
}
