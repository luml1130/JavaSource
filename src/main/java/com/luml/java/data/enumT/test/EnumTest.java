package com.luml.java.data.enumT.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.luml.domain.Person;
import com.luml.java.data.enumT.DisposedTypeEnum;
import com.luml.java.data.enumT.ScoreEnum;
import com.luml.java.data.enumT.TableAlignTypeEnum;
import com.luml.java.data.enumT.UserPo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2025/10/29
 */
public class EnumTest {

    public static void main(String[] args) {
       // String desc = getDescByValue(20);
       // System.out.println(desc);
        Person p = new Person();
        p.setName("dd");
        System.out.println(StringUtils.isEmpty(p.getBigName()));
        String uri = StringUtils.isEmpty(p.getBigName()) ? "/pl/alarmStat/pag" : p.getBigName();
        System.out.println(uri);
        //System.out.println(StringUtils.isEmpty(p.getBigName()) ?"是":"否");
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

    @Test
    public void enum2List(){

        List<Map<String,Object>> enumList = EnumUtil.enumToList(ScoreEnum.class);
        System.out.println(enumList);
        //[{name=A, value=0}, {name=B, value=1}, {name=C, value=2}, {name=D, value=3}]

        List<Map<String,Object>> tableAlignEnumList = EnumUtil.enumToList(TableAlignTypeEnum.class);
        System.out.println("tableAlignEnumList="+tableAlignEnumList);
        //tableAlignEnumList=[{name=LEFT, value=0}, {name=CENTER, value=1}, {name=RIGHT, value=2}]

        //EnumUtil.enumToList不靠谱啊
        List<Integer> typeList = Lists.newArrayList();
        for (TableAlignTypeEnum eventTypeEnum: TableAlignTypeEnum.values()) {
            typeList.add(eventTypeEnum.getId());
        }
        System.out.println(typeList); //[1, 2, 3]
    }

    @Test
    public void implementsTest(){
        ScoreEnum.A.print(); //desc: A
    }

}
