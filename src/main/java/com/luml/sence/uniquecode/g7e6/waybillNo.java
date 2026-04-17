package com.luml.sence.uniquecode.g7e6;

import com.luml.domain.enu.UniqueCodeTypeEnum;
import com.luml.sence.uniquecode.random.RandomUtils;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2026/3/28
 */
public class waybillNo {

    private static final Integer INCREMENT_MAX_ID_LENGTH = 5;


    /**
     * G7E6 订单运单号
     * 从redis中获取编码自增  和内存缓存中的编码规则 组装唯一编码
     * redis的key ： 编码类型 + 时间串YYYYMMDD + 公司固定四位字母串
     *
     * @param uniqueCodeType  编码类型枚举
     * @return
     */
    @Test
    //busi: this.waybillNo = commonTmsGateway.getUniqueCode(UniqueCodeTypeEnum.GENERAL_WAYBILL);
    //public String getUniqueCode(UniqueCodeTypeEnum uniqueCodeType);
    public void getUniqueCode() {
        //UniqueCodeTypeEnum uniqueCodeType = new UniqueCodeTypeEnum();
        //AbstractUser currentUser = CurrentUserHolder.getCurrentUser();
        Integer corpId = 1;//currentUser.getCorpId();
        //ConfigCorpUniqueCodePO uniqueSet = getUniqueSet(corpId); config_corp_unique_code 的prefix_code  EYNQ

        String dateStr = "20260328";//DateUtils.formatDateToString(new Date(), DateStyle.YYYYMMDD);
        String redisIncrementKey = UniqueCodeTypeEnum.GENERAL_WAYBILL.getPrefix()
                .concat(dateStr).concat(corpId.toString());

       // String prefix = uniqueCodeType.getPrefix().concat(dateStr).concat(uniqueSet.getPrefixCode());
        String prefix = UniqueCodeTypeEnum.GENERAL_WAYBILL.getPrefix().concat(dateStr).concat("EYNQ");

        //组装唯一编码

        String maxIdStr;
        try {
            Long maxId =  1L;//redisService.increment(redisIncrementKey, 1, 24, TimeUnit.HOURS); --redis模块
            maxIdStr = String.valueOf(maxId);

            maxIdStr = maxIdStr.length() < INCREMENT_MAX_ID_LENGTH ? String.format("%05d", maxId) : maxIdStr;
        } catch (Exception e) {
            //E6Exception.switchLog(log, e, "redis获取编码规则出错");
            maxIdStr = RandomUtils.getRandomString(INCREMENT_MAX_ID_LENGTH);
        }
        System.out.println(prefix.concat(maxIdStr));//T20260328EYNQ00001
        //return prefix.concat(maxIdStr);
    }



}
