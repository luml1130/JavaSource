package com.luml.java.collection.map;


import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//        键的类型         值的类型

		Map<Integer, String> map = createMap();

		//得到map中键的集合
		Set<Integer> keys = map.keySet();
		for(int key : keys) {
			System.out.println(key+"---"+map.get(key));
		}
		map.remove(6);
		System.out.println("--------------");
	}

	@Test
	public void testEmpty(){
		Map<Long,Object> userMap = null;
		// userMap.size() 空指针异常
		System.out.println(userMap != null && userMap.size() > 0);

		Map<Long,Object> userMap2 = new HashMap<>();
		System.out.println(userMap2.size());

	}

	@Test
	public void mapBianLi(){
		Map<Integer, String> map = createMap();

		//方法一、通过Map.entrySet遍历key和value
		for (Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		//方法二、在for-each循环中遍历keySet或values。
		for (Integer key : map.keySet()) {  //遍历map中的键
			System.out.println("Key = " + key);
		}
		for (String value : map.values()) {  //遍历map中的值
			System.out.println("Value = " + value);
		}

		//方法三、得到map中Entry的集合，一个entry是map中的一个键值对 然后使用迭代器
		Set<Entry<Integer, String>> entrys = map.entrySet();
		//用迭代器遍历集合
		Iterator<Entry<Integer, String>> iter = entrys.iterator();
		while(iter.hasNext()) {
			Entry<Integer, String> entry = iter.next();
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
		//map.forEach();
	}

	@Test
	public void mapBianLi18(){
		Map<Integer, String> map = createMap();

		//方法一、通过Map.entrySet遍历key和value
		/*for (Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}*/
		map.entrySet().forEach(
				entry -> System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue())
		);
		//方法二、在for-each循环中遍历keySet或values。
		/*for (Integer key : map.keySet()) {  //遍历map中的键
			System.out.println("Key = " + key);
		}*/
		map.keySet().forEach(
				key -> System.out.println("map.get(" + key + ") = " + map.get(key))
		);
		/*for (String value : map.values()) {  //遍历map中的值
			System.out.println("Value = " + value);
		}*/
		map.values().forEach(
				value -> System.out.println("Value = " + value)
		);
		//等价于
		map.values().forEach(System.out::println);

		//方法三、得到map中Entry的集合，一个entry是map中的一个键值对 然后使用迭代器
		/*Set<Entry<Integer, String>> entrys = map.entrySet();
		//用迭代器遍历集合
		Iterator<Entry<Integer, String>> iter = entrys.iterator();
		while(iter.hasNext()) {
			Entry<Integer, String> entry = iter.next();
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}*/
		map.entrySet().iterator().forEachRemaining(
				entry -> System.out.println(entry.getKey()+"---"+entry.getValue())
		);
		//map.forEach();
		map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
	}

	public static Map<Integer, String> createMap(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		//往map中存放一个键值对

		map.put(3, "three");
		map.put(4, "four");
		map.put(1, "one");
		map.put(2, "two");
		map.put(5, "five");
		//map集合中键不能重复，重复添加某一个实质是修改
		map.put(3, "six");
		map.put(6, "six");
		return map;
	}

}
