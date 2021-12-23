package com.luml;


import java.util.Objects;

/**
 * @author xiyang
 * @version 1.0
 * @createTime 2020/12/22
 */

public class SyncResult {

    public static final String SUCCESS_CODE = "100000";
    public static final String SUCCESS_MSG = "success";

    public static final String FAIL_UNKNOWN_CODE = "-1";
    public static final String FAIL_UNKNOWN_MSG = "unknown";

    private boolean success;
    private String code;
    private String resultMsg;

    private SyncResult(String code, String resultMsg) {
        this.code = code;
        this.resultMsg = resultMsg;
        this.success = Objects.equals(SUCCESS_CODE, this.getCode());
    }

    public String getCode() {
        return code;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public static SyncResult success(){
        return new SyncResult(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static SyncResult fail(){
        return new SyncResult(FAIL_UNKNOWN_CODE, FAIL_UNKNOWN_MSG);
    }
    public static SyncResult fail(String failCode, String failMsg){
        return new SyncResult(failCode, failMsg);
    }

    public static SyncResult unknownFail(){
        return new SyncResult(FAIL_UNKNOWN_CODE, FAIL_UNKNOWN_MSG);
    }

    public boolean isSuccess(){
        return this.success;
    }

    @Override
    public String toString() {
        return "SyncResult{" +
            "success=" + success +
            ", code='" + code + '\'' +
            ", resultMsg='" + resultMsg + '\'' +
            '}';
    }
}
