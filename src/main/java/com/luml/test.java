package com.luml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import com.luml.domain.City;
import com.luml.juc.lock.volatileTest.Test;

import java.io.File;
import java.util.*;

/**
 * @author luml
 * @description
 * @date 2021/8/23 上午11:47
 */
public class test {

    public static final String GET_EXTERNAL_USER_LIST_URL = "/qw-platform/api/v1/mix/getExternalUserList?suiteId=%s&unionId=%s&openId=%s&corpId=%s";

    public static void main(String[] args) {
        System.out.println("122".startsWith("2"));
        /*String suiteId = "ss";
        String unionId = "unionId";
        String openId ="openId";
        String method = String.format(GET_EXTERNAL_USER_LIST_URL,suiteId,unionId,openId,"");
        System.out.println(method);*/
    }

    public static void main5(String[] args) {
        Map map = new HashMap(2);
        map.put("wxCorpId","ss");
        map.put("messageBus","agree_external_userid_migration");
        map.put("messageType","business_data_migration_finished");
        map.put("business","UC");
        System.out.println(JSON.toJSONString(map));
    }


    public static void main4(String[] args) {

        List<String> externalUserIds = new ArrayList<>();
        externalUserIds.add("ssd");
        System.out.println(externalUserIds.toString());
        System.out.println(JSONObject.toJSONString(externalUserIds));
        System.out.println(Arrays.toString(externalUserIds.toArray()));


        JsonObject jsonObject = new JsonObject();
        //jsonObject.addProperty("external_userid_list",Arrays.toString(externalUserIds.toArray()));
        //jsonObject.addProperty("external_userid_list",new Gson().toJson(externalUserIds));
        jsonObject.addProperty("external_userid_list",JSONObject.toJSONString(externalUserIds));

        System.out.println(jsonObject);

      /*  List<City> aa = new ArrayList<>();

        City city1 = new City();
        city1.setCity("22");
        City city = new City();
        city.setCity("33");
        aa.add(city);
        aa.add(city1);

        //aa.remove(city);
        System.out.println(aa);*/

        /*Map<String, Object> ucAppCache = new HashMap<>();
        ucAppCache.put("1",new Object());
        Set<String> set = ucAppCache.keySet();
        System.out.println(set.toArray()[0]);*/




        /*File file = new File("/opt/1.txt");
        System.out.println(getFileExtension(file));*/
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }else{
            return "";
        }
    }
    public static void main3(String[] args) {
        Integer[] aa = new Integer[3];
        aa[0] = 1;
        aa[1] = 1;
        aa[2] = 1;
        System.out.println(aa.length);
        System.out.println(aa.toString());
       List<Integer> bb =  Arrays.asList(aa);
       for(Integer a : bb){
           System.out.println(a);
       }

    }
    public static void main2(String[] args) {

        try{
            new test().aa();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("---------");
       /* Long a = null;
        System.out.println(a == null ? 0L : a);
        System.out.println(System.currentTimeMillis());*/
        /*System.out.println("XYS".equals("XYS"));
        System.out.println("XYS".equals("xys"));
        List<String> aList = new ArrayList<>();
        for(String a : aList){
            System.out.println(a);
        }*/



        /*Boolean bb = true;
        try{
            SyncResult deptSyncResult = SyncResult.fail();
            if(deptSyncResult.isSuccess()){
                //falag = false;
            }else{
                bb = false;
            }
        }catch (Exception e){
            bb = false;
        }
        System.out.println(bb);*/

    }

    private void aa(){
        int q = 1/0;
    }
}
