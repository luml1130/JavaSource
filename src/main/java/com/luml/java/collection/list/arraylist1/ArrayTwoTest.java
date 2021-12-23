package com.luml.java.collection.list.arraylist1;

public class ArrayTwoTest {
	public static void main(String[] args) {
		/*
		 * 直接初始化：
		 */
		int[][] arr1 = {{1, 2, 3}, {4, 5, 6}, {10, 8, 9}};
		System.out.println(arr1[0][2]);
		
		int[][] arr2 = new int[][]{{4, 5, 6, 7}, {8, 9, 10, 11}};
		System.out.println(arr2[1][3]);
		
		/*
		 * 动态初始化
		 * 4表示行数，5表示每行的元素个数
		 */
		int[][] arr3 = new int[4][5];
		//增强for循环遍历二维数组，二维数组中的每一个元素是一个一维数组
		for(int[] i : arr1){
			for(int j : i) {
				System.out.print(j + "\t");
			}
			System.out.println();
		}
		//普通for循环遍历二维数组
		for(int i=0;i<arr2.length;i++){
			
			for (int j=0;j<arr2[i].length;j++){
				System.out.print(arr2[i][j]+"\t");
			}
			System.out.println();
		}
		
		int sumAsc = 0;		//正向
		int sumDesc = 0;	//反向
		//求对角线元素和
		for(int i=0; i<arr1.length; i++) {
			sumDesc += arr1[i][arr1.length-1-i];
			sumAsc += arr1[i][i];
		}
		System.out.println(sumDesc);
		System.out.println(sumAsc);
	}
}
