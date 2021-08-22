package com.luml.java.collection.list.arraylist1;

public class HackleArrayTest {
	public static void main(String[] args) {
		/*
		 * 直接初始化：
		 */
		int[][] arr1 = {{1, 2, 3}, {4, 5}, {10, 8, 9}};
		System.out.println(arr1[0][2]);
		
		int[][] arr2 = new int[][]{{4, 0, 6}, {8, 9, 10, 11}, {}};
		System.out.println(arr2[1][3]);
		
		/*
		 * 动态初始化
		 * 只给定行数，不给定每一行的元素个数，可以做成锯齿形数组
		 * 将来对于每一行必须分别初始化，才能使用
		 */
		int[][] arr3 = new int[4][];
		arr3[0] = new int[]{1, 2, 3};
		arr3[1] = new int[5];
		System.out.println(arr3[1][0]);
	}
}
