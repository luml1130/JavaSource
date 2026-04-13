package com.luml.java.collection.tree.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author luml
 * @description 报警类型树实体类
 * @date 2025/12/4
 */
public class AlarmTypeTreeVO implements ITreeVO, Serializable {

    /**
     * 报警id
     */
    private String id;

    /**
     * 报警父id
     */
    private String pid;

    /**
     * 报警名称
     */
    private String label;

    private Boolean disabled;

    /**
     * 报警子报警
     */
    private List<ITreeVO> children;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public List<ITreeVO> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<ITreeVO> children) {
        this.children = children;
    }
}
