package com.luml.java.collection.list.arraylist2;

import java.util.Arrays;

public class Serch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[10];
		
		int num = (int)(Math.random()*50+1);
		for(int i=0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*50+1);
		}
		
		Arrays.sort(arr);
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+"\t");
		}
		int index = binarySerch(arr, arr[9]);
		System.out.println();
		System.out.println(arr[9]+"的位置是："+index);
	}
	
	public static int binarySerch(int[] arr, int num) {
		int low = 0;
		int high = arr.length-1;
		int index = (low+high)/2;
		
		while(low<=high) {
			if(arr[index]==num) {
				return index;
			} else if(arr[index]<num) {
				low = index+1;
			} else {
				high = index-1;
			}
			index = (low+high)/2;
		}
		
		return -1;
	}

}
