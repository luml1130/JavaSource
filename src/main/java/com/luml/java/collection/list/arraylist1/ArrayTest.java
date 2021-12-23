package com.luml.java.collection.list.arraylist1;

public class ArrayTest {

	public static void main(String[] args) {
		/*
		 * 直接初始化一个数组：
		 * 类型[] 数组名 = {值}
		 * 这种直接初始化只能和数组的声明写在一行，不能单独存在
		 */
		int[] arr1 = {1, 2, 5, 8, 3, 7, 9};
		System.out.println(arr1[2]);
		
		/*
		 * 直接初始化一个数组：
		 * 类型[] 数组名 = new 类型[]{值}
		 * 这种直接初始化可以分开写，可以做匿名数组
		 */
		int[] arr2;
		arr2 = new int[]{5, 8, 1, 4, 6, 9, 7, 3};
		System.out.println(arr2[4]);
		
		/*
		 * 动态初始化一个数组
		 * 类型[] 数组名 = new 类型[长度]
		 * 动态初始化的数组初始值：基本数据类型--0；类类型--null
		 */
		int a = 10;
		int[] arr3 = new int[a];
		for(int i=0; i<arr3.length; i++) {
			arr3[i] = (int) (Math.random()*100);
			System.out.print(arr3[i]+"\t");
		}
		
		//找数组的最大值
		int max = 0;
		for(int i=1; i<arr3.length; i++) {
			if(arr3[i]>arr3[max]) {
				max = i;
			}
		}
		System.out.println("最大值是："+arr3[max]);
		
		//数组的复制，注意下标越界的问题
		//               源              源起始              目标            目标起始                复制长度
		System.arraycopy(arr1, 2, arr2, 1, 3);
		
		//求数组长度的属性
		System.out.println(arr2.length);
		System.out.println("---------------");
		
		//普通for遍历数组
		for(int i=0; i<arr2.length; i++){
			System.out.println(arr2[i]);
		}
		
		/*
		 * 增强for循环遍历数组
		 * for(类型    变量名 ：数组名或集合名)
		 * 类型是数组中每一个元素的类型
		 * 在遍历的过程中将数组中的每一个元素，依次赋值给变量，直到数组最后一个元素
		 */
		for(int i :arr2){
			System.out.println(i);
		}
	}
}
