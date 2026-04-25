package com.luml.sence.rule.QlExpress;

import com.alibaba.fastjson.JSONObject;
import com.luml.sence.rule.QlExpress.g7e6.ExpressEngine;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author luml
 * @description
 * @date 2026/4/25
 */
public class g7e6Test {
    public static void main(String[] args) {
        System.out.println("333");
    }

    /** 格式化数据引擎 */
    private static final ExpressRunner RUNNER = ExpressEngine.buildFormatRunner();

    /*private static void row(JSONObject rowJSON, List<FieldSetting> formatterFieldList) throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        for(FieldSetting field : formatterFieldList){
            context.put(VARIABLE_LOG, log);
            context.put(VARIABLE_ROW, rowJSON);
            context.put(VARIABLE_FIELD, field);
            final String qlExpressScript = field.getFormatter();
            if (StringUtils.isNotEmpty(qlExpressScript)) {
                RUNNER.execute(qlExpressScript, context, null, true, log.isTraceEnabled());
            }
        }
    }*/
}
