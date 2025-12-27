package com.luml.java.collection.operate.forTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * @author luml
 */
public class IteratorTest {

	public static void main(String[] args) {
		Reverse();
	}

	private static void Reverse(){
		ArrayList<String> list = new ArrayList<String>();

		list.add("1");
		list.add("7");
		list.add("8");
		ListIterator iterator = list.listIterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next());
		}
		//判断若前一个元素存在，则取出来。从而
		while(iterator.hasPrevious()){
			System.out.print(iterator.previous());
		}
		//打印结果为正向排序及逆向排序打印：178871
	}

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();

		list.add("three");
		list.add("four");
		list.add("one");
		list.add("two");
		list.add("five");
		list.add("five");
		
		System.out.println(list);
		
		//得到迭代器
		Iterator<String> iter = list.iterator();
		/*
		 * remove方法移除的是迭代器刚刚跳过的元素
		 * 因此remove前必须先next一次
		 */
		iter.next();
		iter.remove();
		while(iter.hasNext()) {
			//next返回的是刚刚跳过的元素
			System.out.println(iter.next());
		}
		//iter.next();
		System.out.println("--------------");
		System.out.println(list.get(0));
	}

	@Test
	public void foreach18(){
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);

		numbers.forEach((Integer integer) ->{
			System.out.println(integer);
		});

		numbers.forEach(integer -> {
			System.out.println(integer);
		});

		numbers.forEach(integer -> System.out.println(integer));
		numbers.forEach(System.out::println);

	}

}
