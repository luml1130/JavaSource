package com.luml.java.data.enumT;

import com.alibaba.fastjson.JSON;

/**
 * @author luml
 * @description
 * @date 2025/10/29
 */
public class enumTest {

    public static void main(String[] args) {
        String desc = getDescByValue(20);
        System.out.println(desc);
    }

    private static String getDescByValue(Integer score) {
        if (score < 70) {
            return ScoreEnum.D.name();
        }else if(score < 80 && score >= 70){
            return ScoreEnum.C.name();
        }else if (score < 90 && score >= 80){
            return ScoreEnum.B.name();
        }else if((score >= 90)){
            return ScoreEnum.A.name();
        }
        return ScoreEnum.D.name();

    }
    public static void main2(String[] args) {
        UserPo po = new UserPo();
        po.setId(2);
        po.setDisposedTypeEnum(DisposedTypeEnum.WAIT);

        System.out.println(JSON.toJSONString(po));

        //System.out.println(DisposedTypeEnum.WAIT.equals(DisposedTypeEnum.WAIT));

        //System.out.println(OpenSaasTypeEnum.checkParam(3));
       // System.out.println(OpenSaasTypeEnum.getDescByValue(2));
        //System.out.println(OpenSaasTypeEnum.getValueByDesc("开通"));
        //System.out.println(OpenSaasTypeEnum.getValueList());
        //System.out.println(StaticParamEnum.CICADA_DEFULT_ICON);

    }

}
