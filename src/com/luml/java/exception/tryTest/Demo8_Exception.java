package com.luml.java.exception.tryTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo8_Exception {
	public static void main(String[] args) throws IOException,FileNotFoundException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("F:\\Demo1.java");
		} finally {
			fis.close();
		}
	}
}
