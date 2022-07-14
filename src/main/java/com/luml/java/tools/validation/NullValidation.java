package com.luml.java.tools.validation;

import com.alibaba.fastjson.JSONObject;
import com.luml.domain.City;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author luml
 * @description
 * @date 2021/11/23
 */
public class NullValidation {
    public static void main(String[] args) {
        City c = new City();
        c.setAddress("aaa");
        JSONObject externalContact = JSONObject.parseObject(JSONObject.toJSONString(c));
        System.out.println(externalContact.get("city"));
        //String city = externalContact.get("city");
       // packValidation();
        // StringValidation();
        //listValidation();
    }

    public static void packValidation(){
        Long a = 1L;
        System.out.println(a == null);
    }

    /**
     * java.lang包下面的StringUtils
     */
    @Test
    public void StringValidation(){
        String aa = null;
        String bb = "null";
        System.out.println(StringUtils.isEmpty(aa)); //true
        System.out.println(StringUtils.isBlank(aa)); //true
        System.out.println(StringUtils.isEmpty(bb)); //true
        System.out.println(StringUtils.isBlank(bb)); //true
    }

    /**
     * 对象判断为空
     * ！=null
     * 判断对象是否存在：java.utils.Objects.isNull(guard)
     */
    public static void objectValidation(){
        City city = new City();
        //null ！= object
        if(city == null){
            System.out.println("222");
        }
        if(Objects.isNull(city)){
            System.out.println("3333");
        }
    }

    /**
     * 集合判断为空
     * 判断是否为空：CollectionUtils.isEmpty(guardPush.getUserIds()
     * 2、List自己的方法
     * isEmpty() : 用于判断List中元素是否为空，必须在已经分配内存空间的前提下，否则报出异常
     * == null : 用于判断 List 集合是否已经被分配内存空间
     * list.size() == 0 : 与 isEmpty() 方法效果一致，但更推荐使用 isEmpty()
     */
    public static void listValidation(){
        List<Integer> list = null;//new ArrayList<>();
        /*if(CollectionUtils.isEmpty(list)){
            System.out.println("list is empth");
        }*/
        if(list!=null && list.size()>0){
            System.out.println("list is not empty");
        }
        /**未实例化的情况**/
        ArrayList<City> list2 = null;
        System.out.println(null == list2);//返回 true
        System.out.println(list2.isEmpty());// 空指针异常
        /**实例化后的情况**/
        ArrayList<City> list3 = new ArrayList<City>();
        System.out.println(list3.isEmpty());//返回 true
        System.out.println(list3 == null);//返回 false

    }
}
