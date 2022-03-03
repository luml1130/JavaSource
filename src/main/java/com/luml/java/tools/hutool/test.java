package com.luml.java.tools.hutool;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

/**
 * @author luml
 * @description
 * @date 2021/12/23
 */
public class test {

    private static final String salt = "xueJJeduqw";

    public static String ACCESS_TOKEN_URL = "%s/qrcode/wx/rest/getAccessToken?appCode=%s";
    public static String PRODURL = "http://pre-applet.imzhiliao.com";
    public static void main(String[] args) {
        String url = String.format(PRODURL,ACCESS_TOKEN_URL,"dietcare");
        System.out.println(url);
    }

    public static void main2(String[] args) {
        long time = System.currentTimeMillis();
        WxworkMixReq req = new WxworkMixReq();
        req.setTimestamp(System.currentTimeMillis());
        System.out.println("time===========" + time);
        System.out.println("json===========" + JSONUtil.toJsonStr(req));

        String signLocal = SecureUtil.md5(JSON.toJSONString(req) + salt);
        System.out.println("sign===========" + signLocal);

        /*String a = HttpUtil.post("http://localhost:8100/wxwork/mix/listAllSuiteInfo?sign=" +
                SecureUtil.md5(JSONUtil.toJsonStr(req) + salt),JSONUtil.toJsonStr(req));*/
    }
}