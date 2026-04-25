package com.luml.sence.rule.QlExpress.g7e6.check;

import com.ql.util.express.Operator;
import com.ql.util.express.exception.QLException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiqiang@e6yun.com
 * @date 2022/8/10
 */
@Slf4j
abstract class AbstractCheckOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) {
        try {
            doCheck(list);
        } catch (Exception e) {
            log.error("参数校验错误", e);
           // throw new E6UnanticipatedException("参数错误");
        }
        return null;
    }

    /**
     * 执行校验
     * @param list 参数
     * @throws QLException 动态脚本执行异常
     */
    abstract void doCheck(Object[] list) throws QLException;
}
