package com.luml.java.data.enumT;

/**
 * @Description 异常上报明细表处理状态枚举
 *      待处理：驳回后待司机端进行修改
 * @author liuke@e6yun.com
 * @Created Date: 2021/2/4 13:52
 * @ClassName DisposedTypeEnum
 * @Remark
 */
public enum DisposedTypeEnum  {
    WAIT(0,"未处理"),
    YES(1,"通过"),
    NO(2,"驳回"),
    BLOCK(3,"待处理");

    private final Integer statusType;
    private final String statusName;

    public Integer getStatusType() {
        return statusType;
    }

    public String getStatusName() {
        return statusName;
    }

    DisposedTypeEnum(Integer statusType, String statusName) {
        this.statusType = statusType;
        this.statusName = statusName;
    }

}
