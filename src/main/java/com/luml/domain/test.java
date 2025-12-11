package com.luml.domain;

import com.alibaba.fastjson.JSON;
import com.luml.java.data.enumT.TableAlignTypeEnum;
import com.luml.java.data.enumT.TableFixedTypeEnum;

/**
 * @author luml
 * @description
 * @date 2025/12/10
 */
public class test {

    public static void main(String[] args) {
        /**
         *
         ROUTE_DEVIATION_ALARM("011","线路偏移", "routeDeviationCount","01",true),
         LIQUID_REDUCE_ALARM("012","液位异常", "liquidReduceCount","01",true),
         RUN_OUT_TIME("013","运行超时", "runOutTimeCount","01",true),


         */
        //0是true
        SystemField field = new SystemField();
        field.setId("1158664278195466240");
        field.setCorpId(1);
        field.setName("liquidReduceCount");
        field.setLabel("液位异常");

        field.setSceneItem("");
        field.setOrder(4);
        field.setItemScene("");

        field.setTableEnable(true);
        field.setTableHidden(false);
        field.setTableAlign(TableAlignTypeEnum.LEFT);
        field.setTableFixed(TableFixedTypeEnum.LEFT);
        field.setTableWidth(150);
        field.setTableColLocation("");
        field.setTableFormatter("");
        field.setTableVueConfig(null);
        field.setTableDisplay(false);
        field.setTableSearchDisplay(true);//?
        field.setTableSearchEnable(false);
        field.setTableSearchHidden(false);

        field.setFieldValueType(8);
        field.setFormChecker("");
        field.setFormColLocation("");
        field.setFormEnable(false);
        field.setFormRequired(0);
        field.setFormRequiredDisabled(0);
        field.setFormUpdate(false);

        field.setDetailEnable(false);
        field.setDetailColLocation("");
        field.setDetailFormatter("");

        field.setOmpDisable(false);
        field.setOmpDisplay(false);

        field.setImportEnable(false);
        field.setImportRemark("");
        field.setImportRequired(false);
        field.setImportExplicit(null);

        System.out.println(JSON.toJSONString(field));
    }
}
