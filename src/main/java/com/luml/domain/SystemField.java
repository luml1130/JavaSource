package com.luml.domain;

import com.alibaba.fastjson.JSONObject;
import com.luml.java.data.enumT.TableAlignTypeEnum;
import com.luml.java.data.enumT.TableFixedTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luml
 * @description
 * @date 2025/12/10
 */
@Data
public class SystemField {

    private static final String DEFAULT_SCENE_ITEM = "default";


    /**
     * 数据库主键id
     */
    @EqualsAndHashCode.Include
    private String id;
    /**
     * 租户ID
     */
    private Integer corpId;
    /**
     * 字段英文名
     */
    private String name;
    /**
     * 字段中文名
     */
    private String label;
    /**
     * 对应旧版的“sceneId”，一般在表单或者详情页使用，用于分栏位
     */
    private String sceneItem;
    /**
     * 默认排序
     */
    private Integer order;

    /**
     * 分栏
     */
    private String itemScene;

    /**
     * 是否为查询页字段
     */
    private Boolean tableEnable;
    /**
     * 表格列是否可隐藏
     */
    private Boolean tableHidden;
    /**
     * 对齐方式
     */
    private TableAlignTypeEnum tableAlign;
    /**
     * 是否固定
     */
    private TableFixedTypeEnum tableFixed;
    /**
     * 字段宽度
     */
    private Integer tableWidth;
    /**
     * 表格列字段定位
     */
    private String tableColLocation;
    /**
     * 表格列自定义格式化
     */
    private String tableFormatter;
    /**
     * 预留：字段渲染
     */
    private JSONObject tableVueConfig;
    /**
     * 字段：基于个性设置，没有被禁用，只是不显示在页面上
     */
    private Boolean tableDisplay;

    /**
     * 是否启用报表搜索配置
     */
    private Boolean tableSearchEnable;
    /**
     * 是否可隐藏
     */
    private Boolean tableSearchHidden;
    /**
     * 查询条件是否显示
     */
    private Boolean tableSearchDisplay;


    /**
     * 是否为查询页字段
     */
    private Boolean formEnable;
    /**
     * 是否必填
     */
    private Boolean formRequired;
    /**
     * 是否可修改（回显）
     */
    private Boolean formUpdate;
    /**
     * 在表单中找到该字段
     */
    private String formColLocation;
    /**
     * 表单验证
     */
    private String formChecker;
    /**
     * 是否可修改表单是否必填项目
     */
    private Boolean formRequiredDisabled;

    /**
     *
     */
    private Integer fieldValueType;
    /**
     * 是否为详情页字段
     */
    private Boolean detailEnable;
    /**
     * 情页数据中找到该字段
     */
    private String detailColLocation;
    /**
     * 自定义格式化
     */
    private String detailFormatter;

    /**
     * true-运营后台可以禁用此字段
     */
    private Boolean ompDisable;
    /**
     * true-运营后台已禁用此字段
     */
    private Boolean ompDisplay;

    private Boolean importEnable;
    private Boolean importRequired;
    private String importRemark;
    private Object importExplicit;

    /**
     * 场景名称，默认为"default"
     *
     * @return 场景名称
     */
    public String getSceneItem() {
        return StringUtils.isBlank(this.sceneItem) ? DEFAULT_SCENE_ITEM : this.sceneItem;
    }

    public Boolean getOmpDisplay() {
        return Objects.isNull(ompDisplay) ? Boolean.TRUE : this.ompDisplay;
    }


    /**
     * 表单项是否必填
     *
     * @param requiredFlag
     */
    public void setFormRequired(Integer requiredFlag) {
        this.formRequired = Optional.ofNullable(FromRequiredEnum.getById(requiredFlag))
                .map(FromRequiredEnum::isRequired)
                .orElse(Boolean.FALSE);
    }

    /**
     * 表单必填项是否可更改
     *
     * @param requiredFlag
     */
    public void setFormRequiredDisabled(Integer requiredFlag) {
        this.formRequiredDisabled = Optional.ofNullable(FromRequiredEnum.getById(requiredFlag))
                .map(FromRequiredEnum::isRequiredDisabled)
                .orElse(Boolean.FALSE);
    }

    @Getter
    private enum FromRequiredEnum {

//        1-必填项;2-非必填;3-必填且不可修改;4-非必填且不可修改
        /**
         * 必填，可修改
         */
        REQUIRED_ALLOW(1, Boolean.TRUE, Boolean.FALSE),
        /**
         * 非必填，不可修改
         */
        NOT_REQUIRED_ALLOW(2, Boolean.FALSE, Boolean.FALSE),
        /**
         * 必填，不可修改
         */
        REQUIRED_DISABLED(3, Boolean.TRUE, Boolean.TRUE),
        /**
         * 非必填，不可修改
         */
        NOT_REQUIRED_DISABLED(4, Boolean.FALSE, Boolean.TRUE);

        private final int id;
        private final boolean required;
        private final boolean requiredDisabled;

        private static final Map<Integer, FromRequiredEnum> ID_MAPPER = Arrays.stream(FromRequiredEnum.values())
                .collect(Collectors.toMap(FromRequiredEnum::getId, e -> e));

        FromRequiredEnum(int id, boolean required, boolean requiredDisabled) {
            this.id = id;
            this.required = required;
            this.requiredDisabled = requiredDisabled;
        }

        static FromRequiredEnum getById(Integer id) {
            return ID_MAPPER.get(id);
        }

        static FromRequiredEnum getByRequired(Boolean required, Boolean requiredDisabled) {
            if (Optional.ofNullable(required).orElse(Boolean.FALSE)) {
                return Optional.ofNullable(requiredDisabled).orElse(Boolean.FALSE) ? REQUIRED_DISABLED : REQUIRED_ALLOW;
            } else {
                return Optional.ofNullable(requiredDisabled).orElse(Boolean.FALSE) ? NOT_REQUIRED_DISABLED : NOT_REQUIRED_ALLOW;
            }
        }
    }

}


