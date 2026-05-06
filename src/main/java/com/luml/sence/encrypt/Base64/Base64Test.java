package com.luml.sence.encrypt.Base64;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luml
 * @description
 * @date 2026/5/6
 */
public class Base64Test {

    @Test
    public void UemTest() {
        //Basic Auth example
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("principal", "zs");
        userMap.put("pinHash", "111111");
        userMap.put("tenantId", "mdm");
        userMap.put("emmServerId", "1");
        userMap.put("emmServerName", "1");

        Map<String, String> header = new HashMap<String, String>();
        String token = BASE64.encryptBASE64(  "admin:admin");
        System.out.println(token);//YWRtaW46YWRtaW4=
        header.put("Authorization", "Basic " + token);
        //header.put("ContentType","application/json");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("identifier", "1aqwezqwqadfq");
        //String url = "http://192.168.22.117:8080/uem/api/v1/app";
        String url = "http://192.168.22.123:8080/uem/api/v1/queryAllNetCardsInfo";
       // RestTemplate restTemplate = RestTemplateFactory.getDefaultRestTemplate();
        //Map map = RestAPIUtil.postForEntity(restTemplate, url, params, header, new Object[]{});
       // Map map = RestAPIUtil.getForEntity(url, header, new Object[]{});

        //String netCardNumber = (String) map.get("NetCardNumber");

    }

}
