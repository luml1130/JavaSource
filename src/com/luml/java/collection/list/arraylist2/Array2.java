package com.luml.java.collection.list.arraylist2;

public class Array2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = (int) (Math.random()*5+3);
		int[][] arr = new int[size][size];
		int[] arrRowSum = new int[size];
		int[] arrColumnSum = new int[size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arr[i][j] = (int) (Math.random()*10);
			}
		}
		
		System.out.println("----------");
		
		for(int i=0; i<size*size; i++) {
			if(i>0&&i%size==0) {
				System.out.println();
			}
			System.out.print(arr[i/size][i%size]+"\t");
		}
		System.out.println("------------");
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				arrRowSum[i] += arr[i][j];
				arrColumnSum[i] += arr[j][i];
			}
		}
		
		sort(arrColumnSum);
		for(int i : arrColumnSum) {
			System.out.println(i);
		}
	}
	
	public static void sort(int[] arr) {
		int temp = 0;
		for(int j=0; j<arr.length-1; j++) {
			int max = 0;
			for(int i=1; i<=arr.length-1-j; i++) {
				if(arr[i]>arr[max]) {
					max = i;
				}
			}
			if(max!=arr.length-1-j) {
				temp = arr[max];
				arr[max] = arr[arr.length-1-j];
				arr[arr.length-1-j] = temp;
			}
		}
	}

}
