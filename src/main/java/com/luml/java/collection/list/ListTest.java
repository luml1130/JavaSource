package com.luml.java.collection.list;


import com.alibaba.fastjson.JSON;
import com.luml.domain.DriverPushVo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

public class ListTest {
	public static void main(String[] args) {
		DriverPushVo pushVo = null;
		if(true){
			pushVo = DriverPushVo.builder().name("aa").phone("137323").build();
			if(true){
				pushVo.setPhone("2323");
				pushVo.setIsUpdatePhone(true);
			}
		}
		DriverPushVo pushDto = null;
		if(pushVo.getIsUpdatePhone()){
			pushDto = DriverPushVo.builder().name(pushVo.getName())
					.phone(pushVo.getPhone())
					.oldPhone(pushVo.getOldPhone()).build();
		}else{
			pushDto = DriverPushVo.builder().name(pushVo.getName()).phone(pushVo.getPhone()).build();
		}
		System.out.println(JSON.toJSONString(pushDto));
	}


	public static void removeTest() {
		List<String> gList = new ArrayList<String>();
		gList.add("aaa");          gList.add("bbb");          gList.add("ccc");
		gList.add("ddd");          gList.add("eee");          gList.add("fff");
		gList.add("ggg");          gList.add("111");          gList.add("222");
		gList.add("333");          gList.add("444");          gList.add("555");
		//List<String> dbList = new ArrayList<String>();
		//dbList.add("aaa");         dbList.add("ddd");          dbList.add("eee");
		int i = 0;
		for(String a : gList){
			i++;
			System.out.println(a);
			if(i > 3){
				break;
			}
		}
		System.out.println("跳出了");

//		List<String> otherLinkPageIdList = new ArrayList(gList);;//new ArrayList<String>(gList.subList(2+1,gList.size()));
//		int i=4;
//		System.out.println(gList.size());
//
//		if(otherLinkPageIdList.size()>10){ //即第二到第11页
//			otherLinkPageIdList =  new ArrayList<String>(gList.subList(0,10));
//		}
		/*if(gList.size()>10){
			if(gList.size()>i+10){
				otherLinkPageIdList = otherLinkPageIdList.subList(i+1,i+10);
			}else{
				otherLinkPageIdList = otherLinkPageIdList.subList(i+1,gList.size());
			}
		}else{
			otherLinkPageIdList.remove(i);
		}*/

		//otherLinkPageIdList.add(0,"lu");
		//System.out.println(otherLinkPageIdList);

	}

	public static void subListTest() {
		List<String> aList = new ArrayList<String>();
		aList.add("111");          aList.add("222");          aList.add("333");aList.add("444");
		aList.add("555");		  aList.add("666");          aList.add("777");      aList.add("888");
		aList.add("999");   aList.add("10");		  aList.add("11");       aList.add("12");
		aList.add("13");
		List<String> bList = new ArrayList<String>();
		bList.add("aaa");          bList.add("ddd");          bList.add("eee");

		aList.remove("eee");


		int index = aList.indexOf("111");
		System.out.println("index="+index);
		System.out.println("size="+aList.size());
		//第二页  要三到11 再加上首页
		List<String> cList =  new ArrayList<String>();
		if(aList.size()>=index+10){
			cList = aList.subList(index+1,index+10);
			cList.add(0,"111");
		}else{
			cList = aList.subList(index+1,aList.size());
			//cList.remove("666");
		}
		for (String s : aList) {
			System.out.println(s);
		}

	}

	/**
	 * 3种遍历方法
	 */
	@Test
	public void forTest() {
		/*
		 * 集合中存放的数据类型是Object
		 * ArrayList list = new ArrayList();
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		//添加元素
		for(int i=0; i<5; i++) {
			list.add((int) (Math.random()*100));
		}
		
		//获取元素
		System.out.println(list.get(1));
		//获取集合的长度
		System.out.println(list.size());
		
		int sum = list.get(0)+list.get(1);
		System.out.println(sum);
		
		System.out.println("----------");
		
		//遍历

		for(int i =0;i < list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println("----------");

		for(int num : list) {
			System.out.println(num);
		}
		System.out.println("----------");

		Iterator<Integer> it =  list.iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
}
