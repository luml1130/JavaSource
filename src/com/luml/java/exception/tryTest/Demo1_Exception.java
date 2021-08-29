package com.luml.java.exception.tryTest;

public class Demo1_Exception {
	public static void main(String[] args)  {
	//	try{
			int[] arr = new int[5];
			System.out.println(arr[5]);	//ArrayIndexOutOfBoundsException
			
			arr = null;   //如果这里不写；报java.lang.Error: Unresolved compilation problem: 
	        //System.out.println(arr[0]);		//NullPointerException
		//}catch( Exception e){
		//  System.out.println(e.getMessage());	
		//  System.out.println(e.getStackTrace());
//		  e.printStackTrace();
//		  System.out.println(e.printStackTrace());
		//}
		
	}
}
