package com.luml.java.collection.list.arraylist2;

public class Triangle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = new int[7][];
		for(int i=0; i<arr.length; i++) {
			arr[i] = new int[i+1];
			arr[i][0] = 1;
			arr[i][i] = 1;
			for(int j=1; j<arr[i].length-1; j++) {
				arr[i][j] = arr[i-1][j]+arr[i-1][j-1];
			}
		}
		
		for(int[] arr1 : arr) {
			for(int num : arr1) {
				System.out.print(num+"\t");
			}
			System.out.println();
		}
	}

}
