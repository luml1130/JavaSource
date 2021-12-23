package com.luml.java.collection.map;

import java.util.LinkedHashMap;

public class bianliMap {
	public static void main(String[] args) {
		method3();
	}
	
	
	public static void method3(){
		//LinkedHashMap map4 = new LinkedHashMap<>();  //1.7的
		LinkedHashMap map4 = new LinkedHashMap<String,String>();
		map4.put("1", "B");
		map4.put("3", "C");
		System.out.println(map4);
		/*Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<Integer,Integer> entry = iterator.next();
			 System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}*/
	}

}
