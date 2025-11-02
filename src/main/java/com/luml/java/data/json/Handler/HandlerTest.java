package com.luml.java.data.json.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.luml.java.data.json.picureVo;

import java.util.List;

/**
 * @author luml
 * @description
 * @date 2025/10/29
 */
public class HandlerTest {

    public static void main(String[] args) {
        /**
         * 参考edu-uc
         * TypeReference typeReference = new TypeReference<List<UcSchoolSimpleDTO>>() {};
         * 	List<UcSchoolSimpleDTO> xySchoolResponseList =
         * 	    (List<UcSchoolSimpleDTO>) JSON.parseObject(schoolMap.get("data").toString(), typeReference);
         */
        String json ="[{\"url\":\"\",\"url_type\":1,\"remark\":\"\"}," +
                "{\"url\":\"\",\"url_type\":1,\"remark\":\"太模糊了\"}]";
        TypeReference typeReference = new TypeReference<List<picureVo>>() {};
        List<picureVo> bb = (List<picureVo>) JSON.parseObject(json,typeReference);
        //List<picureVo> bb = BaseJsonListTypeHandler<picureVo.class>.
        try {
            System.out.println(bb.size());
            System.out.println(bb);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
