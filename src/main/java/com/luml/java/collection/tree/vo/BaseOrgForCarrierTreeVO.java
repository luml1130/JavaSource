package com.luml.java.collection.tree.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class BaseOrgForCarrierTreeVO implements ITreeVO, Serializable {

    private static final long serialVersionUID = -6304090367549506510L;

    /**
     * 组织架构id
     */
    private String id;

    /**
     * 父部门id
     */
    private String pid;

    /**
     * 组织架构名称
     */
    private String label;

    private String orgName;

    private Boolean disabled;

    /**
     * 组织架构子部门
     */
    private List<ITreeVO> children;

    /**
     * 该部门下的车辆总数
     */
    private Integer vehicleCount;

    /**
     * 部门代码
     */
    private String orgCode;
    /**
     * 延长一二期code
     */
    private String orgCodeExternal;
    /**
     * 上级部门名称
     */
    private String pOrgName;
    /**
     * 结算主体名称
     */
    private String subjectName;
    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;
    /**
     * 修改人
     */
    private String modifiedUserName;

    /**
     * 树形条件符合条件的高亮
     */
    private Boolean highLight = Boolean.FALSE;



    private Boolean affectChild;
    private String affectChildStr;

    /**
     * 数据来源 0：平台维护 1：IAM同步
     */
    private Integer dataSource;

    private String dataSourceStr;
}
