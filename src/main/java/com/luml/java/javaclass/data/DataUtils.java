package com.luml.java.javaclass.data;

import java.math.BigDecimal;

/**
 * @author luml
 * @description
 * @date 2021/11/26
 */
public class DataUtils {

    /**
     * 将分为单位的转换为元 （除100）
     * @param amount
     * @return
     */
    public static String feeToYuan(String amount){
        /**金额为分的格式 */
        if(!amount.matches("\\-?[0-9]+")) {
            //logger.error(" FuYouFile 金额格式有误");
            return "0";
        }

        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }
}
