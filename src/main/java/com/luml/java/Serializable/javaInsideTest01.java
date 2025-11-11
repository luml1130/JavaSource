package com.luml.java.Serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class javaInsideTest01 {
	public static void main(String[] args) throws Exception {
		//序列化Person对象
		SerializePerson();
		//反序列Perons对象
		Person person = DeserializePerson();
		System.out.println(person.getAge()+"==="+person.getName()+"==="+person.getSex());
	}
	
	//序列化Person对象
	private static void SerializePerson() throws FileNotFoundException, IOException{
		Person person = new Person();
		person.setAge(25);
		person.setName("gacl");
		person.setSex("男");
		// ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("F:/Person.txt")));
		oo.writeObject(person);
		System.out.println("Person对象序列化成功");
		oo.close();		
	}
	
	//反序列化Person对象
	private static Person DeserializePerson() throws Exception, IOException {
		ObjectInputStream ois = new  ObjectInputStream(new FileInputStream(new File("F:/Person.txt")));
		Person person = (Person) ois.readObject();
		System.out.println("Person对象反序列化成功");
		ois.close();
		return person;	
	}

}
