package com.luml.java.data.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luml.java.data.UserVo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author luml
 * @description: alibaba fastJson
 * @date 2020/3/23 15:45
 */
public class FastJsonTest2 {

    private UserVo user = null;

    public static void main(String[] args) {
        //Map2Str();
        //List2Str();
    }

    public static void main3(String[] args) {
        /**
         * map排序问题
         */
        String aa = "{\"0\":\"姓名\",\"1\":\"岗位\",\"2\":\"手机号\",\"3\":\"月份\",\"4\":\"基本工资\",\"5\":\"全勤\",\"6\":\"职务\"" +
                ",\"7\":\"工龄奖\",\"8\":\"安全奖\",\"9\":\"带班奖\",\"10\":\"绩效\",\"11\":\"用餐补助\",\"12\":\"效益奖金\"" +
                ",\"13\":\"岗位奖\",\"14\":\"养老\",\"15\":\"医疗\",\"16\":\"扣：养老\",\"17\":\"扣：餐费\",\"18\":\"扣：其他\"" +
                ",\"19\":\"扣：医保\",\"20\":\"其他\",\"21\":\"通过的方式\",\"22\":\"年限工资\",\"23\":\"岗位工资\",\"24\":\"应发合计\"}";
        System.out.println(JSON.parseObject(aa));
        /**
         * {"22":"年限工资","23":"岗位工资","24":"应发合计","10":"绩效","11":"用餐补助","12":"效益奖金","13":"岗位奖","14":"养老","15":"医疗"
         * ,"16":"扣：养老","17":"扣：餐费","18":"扣：其他","19":"扣：医保","0":"姓名","1":"岗位","2":"手机号","3":"月份","4":"基本工资","5":"全勤"
         * ,"6":"职务","7":"工龄奖","8":"安全奖","9":"带班奖","20":"其他","21":"通过的方式"}
         */
        System.out.println(new JSONObject(JSON.parseObject(aa,LinkedHashMap.class)).toJSONString());
        JSONObject.parseObject(aa,LinkedHashMap.class);
        JSONObject jsonObject = new JSONObject(true);
        //jsonObject.putAll();
    }

    public static void parseArray(){
        String aa = "{\n" +
                "    \"code\": 200,\n" +
                "    \"data\": [\n" +
                "        \"7天食谱\"\n" +
                "    ],\n" +
                "    \"msg\": \"操作成功\",\n" +
                "    \"success\": true\n" +
                "}";
        Map<String, Object> resultMap = JSON.parseObject(aa, HashMap.class);
        String code = resultMap.get("code") + "";
        if ("200".equals(code)) {
            if (null != resultMap.get("data")) {
                List<String> bb = JSONObject.parseArray(JSONObject.toJSONString(resultMap.get("data")), String.class);
                //System.out.println(Arrays.toString());
                System.out.println(org.apache.commons.lang3.StringUtils.join(bb, ",")+"需要删除重建");
            }
        }
    }

    public static void List2Str(){
        List<String> aa = new ArrayList<>();
        aa.add("土豆");
        aa.add("鸡翅");
        String bb = JSON.toJSONString(aa);
        System.out.println(bb);
        String jsonStr = "[\"土豆\",\"鸡翅\"]";
        List<String> cc  = (List<String>) JSON.parse(jsonStr);
        System.out.println(cc.get(0));

    }

    public static void Map2Str() {
        //map --json
        Map<Integer,Object> map = new HashMap<>();
        map.put(1,"x姓名");
        map.put(2,"手机号");
        String json = JSON.toJSONString(map);
        System.out.println(json);

        Map<String,Object> map2 = new LinkedHashMap<>();
        map2.put("1","x姓名");
        map2.put("2","手机号");
        String json2 = new JSONObject(map2).toJSONString();
        System.out.println(json2);

        Map maps = (Map) JSON.parse(json);
        Iterator<Map.Entry<Integer,String>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,String> item = iterator.next();
            iterator.remove();
        }

        System.out.println(maps);
        /*for (Iterator<Map.Entry<Integer,String>> it = maps.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Integer, String> item = it.next();
            System.out.println(item.getKey());
            System.out.println(item.getValue());
        }*/

        /*for(Object a : maps.keySet()){
            System.out.println(a);
            System.out.println(maps.get(a));
        }*/

        //System.out.println(RandomBatchNum());

    }

    private static String RandomBatchNum(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(new Date());
        int max=24;
        int min=3;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        StringBuffer buffer =new StringBuffer();
        for(int i=0;i<s;i++)
        {
            Integer val = (int)(Math.random()*9+1);
            buffer.append(val.toString());
        }
        return format+buffer.toString();
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     * 比如：610-->00610
     * @param code
     * @return
     */
    private static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数　　// 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);

        return result;

    }

    /*@Before
    public void init() {
        user = new UserVo();        user.setName("zzh");        user.setAge(18);
        UserVo f1 = new UserVo();        f1.setName("jj");        f1.setAge(17);
        UserVo f2 = new UserVo();        f2.setName("qq");        f2.setAge(19);
        List friends = new ArrayList();        friends.add(f1);        friends.add(f2);
        user.setFriends(friends);
    }*/

    /*@Test
    public void writeJson() {
        String str = JSON.toJSONString(user);
        System.out.println(str);
        System.out.println(str.length());
    }*/

    /*@Test
    public void readJson() {
        String serString = "{\"name\":\"zzh\",\"age\":18,\"friends\":[{\"name\":\"jj\",\"age\":17}," +
                "{\"name\":\"qq\",\"age\":19}]}";
        UserVo userVo = JSON.parseObject(serString, UserVo.class);
        System.out.println(userVo.getName());
    }*/
}