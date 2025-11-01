package com.luml.java.data.enumT;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 企微数据同步策略枚举
 *      使用：
 *public static WxDataSyncStrategy getSyncStrategy(Integer departmentType, String changeType) {
        String className = WxWorkDataSyncStrategyEnum.getSyncStrategyClassName(departmentType, changeType);
       WxDataSyncStrategy strategy = strategyBean.get(className);
        if (strategy != null) {
         return strategy;
        }
        Class<?> classz = Class.forName(className);
        //这里必须强行纳入spring管理，否则在得到的具体实现类中，如果有通过@Autowired注入的bean，将会报注入失败
        WxDataSyncStrategy wxWorkDataSyncStrategy =
            (WxDataSyncStrategy) SpringContextUtils.getApplicationContext()
             .getAutowireCapableBeanFactory().createBean(classz, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        strategyBean.put(className, wxWorkDataSyncStrategy);
        return wxWorkDataSyncStrategy;
 * }
 * @Author: 山宏涛
 * @Date: 2021/7/12 16:39
 * @Version 1.0
 */
//@Slf4j
public enum WxWorkDataSyncStrategyEnum implements Serializable {

    ZONE_ADD(DepartmentTypeEnum.ZONE.getCode(), "create_department", "cn.eduplus.uc.wxwork.strategy.sync.zone.ZoneAddStrategy"),
    ZONE_DELETE(DepartmentTypeEnum.ZONE.getCode(), "delete_department", "cn.eduplus.uc.wxwork.strategy.sync.zone.ZoneDeleteStrategy"),
    ZONE_UPDATE(DepartmentTypeEnum.ZONE.getCode(), "update_department", "cn.eduplus.uc.wxwork.strategy.sync.zone.ZoneUpdateStrategy"),

    STAGE_ADD(DepartmentTypeEnum.SCHOOLTYPE.getCode(), "create_department", "cn.eduplus.uc.wxwork.strategy.sync.stage.StageAddStrategy"),
    STAGE_DELETE(DepartmentTypeEnum.SCHOOLTYPE.getCode(), "delete_department", "cn.eduplus.uc.wxwork.strategy.sync.stage.StageDeleteStrategy"),
    STAGE_UPDATE(DepartmentTypeEnum.SCHOOLTYPE.getCode(), "update_department", "cn.eduplus.uc.wxwork.strategy.sync.stage.StageUpdateStrategy"),

    GRADE_ADD(DepartmentTypeEnum.GRADE.getCode(), "create_department", "cn.eduplus.uc.wxwork.strategy.sync.grade.GradeAddStrategy"),
    GRADE_DELETE(DepartmentTypeEnum.GRADE.getCode(), "delete_department", "cn.eduplus.uc.wxwork.strategy.sync.grade.GradeDeleteStrategy"),
    GRADE_UPDATE(DepartmentTypeEnum.GRADE.getCode(), "update_department", "cn.eduplus.uc.wxwork.strategy.sync.grade.GradeUpdateStrategy"),


    CLASS_ADD(DepartmentTypeEnum.CLASS.getCode(), "create_department", "cn.eduplus.uc.wxwork.strategy.sync.classes.ClassAddStrategy"),
    CLASS_DELETE(DepartmentTypeEnum.CLASS.getCode(), "delete_department", "cn.eduplus.uc.wxwork.strategy.sync.classes.ClassDeleteStrategy"),
    CLASS_UPDATE(DepartmentTypeEnum.CLASS.getCode(), "update_department", "cn.eduplus.uc.wxwork.strategy.sync.classes.ClassUpdateStrategy");


    //部门类型
    private Integer departmentType;
    //事件类型
    private String changeType;
    //具体对应策略类（全路径）
    private String className;

    WxWorkDataSyncStrategyEnum(Integer departmentType, String changeType, String className) {
        this.departmentType = departmentType;
        this.changeType = changeType;
        this.className = className;
    }


    /**
     * 根据部门类型和事件类型获取具体策略处理实现类
     *
     * @param departmentType
     * @param changeType
     * @return
     */
    public static String getSyncStrategyClassName(Integer departmentType, String changeType) {

        for (WxWorkDataSyncStrategyEnum c : WxWorkDataSyncStrategyEnum.values()) {
            if (c.getDepartmentType().equals(departmentType)
                    && c.getChangeType().equals(changeType)) {
                return c.getClassName();
            }
        }
        return null;
    }


    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
