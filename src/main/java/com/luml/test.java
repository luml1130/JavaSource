package com.luml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.luml.domain.Person;
import com.luml.java.tools.Sercuity.uem.MD5;

import java.io.File;
import java.util.*;

/**
 * @author luml
 * @description
 * @date 2021/8/23 上午11:47
 */
public class test {
    public static void main(String[] args) {
        //String md = MD5.md5("184CD4DF-C5D2-4F55-8539-ED45D4E91FBBappkeyabe10b89-8185-483a-b28f-894f7957f78dformatjsonmethodGetVehcileInfosessionidtimestamp2014-11-08 11:47:42vehicle陕A0000184CD4DF-C5D2-4F55-8539-ED45D4E91FBB");

        String md = MD5.md5("8D6B64F4-B6C1-4EAD-8860-34E476146FDC" +
                "appkey3ED37D32-30D7-4984-9E4B-0DB3A504AC5Bformatjson" +
                "methodsyncsessionidtimestamp2025-12-16 13:40:42nameceshi" +
                "8D6B64F4-B6C1-4EAD-8860-34E476146FDC");
        System.out.println(md);
        //1ee16d3d4c0964bbfc583c69ff7b1f52
        String sign = "1EE16D3D4C0964BBFC583C69FF7B1F52";

    }
    public static void main7(String[] args) {

        Person p = new Person();
        p.setAge(2);

        System.out.println(p.getAge() * 2);
        System.out.println("aaa");
        /*String path="/Users/luml/Downloads/luml";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }*/

    }

    public static final String GET_EXTERNAL_USER_LIST_URL = "/qw-platform/api/v1/mix/getExternalUserList?suiteId=%s&unionId=%s&openId=%s&corpId=%s";

    public static void main6(String[] args) {
        Long[] department = new Long[]{3L};
        List<Long> newRelationList = Arrays.asList(department);
        System.out.println(newRelationList);
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
