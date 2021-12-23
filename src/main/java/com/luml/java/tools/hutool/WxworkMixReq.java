package com.luml.java.tools.hutool;

/**
 * @author luml
 * @description
 * @date 2021/12/23
 */
public class WxworkMixReq {
    private String corpId;

    private String suiteId;

    private Long timestamp;

    public WxworkMixReq() {
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
