package com.luml.java.collection.map;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.Map;

//org.apache.commons » commons-collections4


public class CaseInsensitiveMapTest {
		public static void main(String[] args) {
			Map<String, Object> result = new CaseInsensitiveMap();
			result.put("abc", 2);
			System.out.println(result.get("ABC"));
		}

}
