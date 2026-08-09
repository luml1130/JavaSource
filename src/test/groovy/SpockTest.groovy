

import spock.lang.Specification
import spock.lang.Unroll

class SpockTest extends Specification{
    def "两数相加应正确"() {
        given: "准备输入"
        def a = 1
        def b = 2

        when: "执行计算"
        def result = a + b

        then: "验证结果"
        result == 3
    }

    @Unroll // 开启参数化测试命名
    def "#a + #b 应等于 #expected"() {
        expect: "简化写法：直接断言"
        a + b == expected

        where: "数据表"
        a | b || expected
        1 | 2 || 3
        0 | 0 || 0
        -1| 1 || 0
    }
}
