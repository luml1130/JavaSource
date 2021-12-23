package com.luml.java.javaclass.studyD10;

class Demo18_Abstract {
	public static void main(String[] args) {
		BaseTeacher bt = new BaseTeacher("鲁孟良","216");
		bt.teach();
	}
}


/*
老师示例，根据给出内容设计继承体系
具体事物：基础班老师，就业班老师
共性：姓名，所属教室，讲课。
*/

abstract class Teacher {
	private String name;							//定义老师的姓名
	private String classRoom;						//定义老师的所属教室

	public Teacher(){}								//定义空参数构造方法

	public Teacher(String name, String classRoom) {	//定义有参数的构造方法
		this.name = name;							//为子类继承创建对象提供服务
		this.classRoom = classRoom;
	}

	public void setName(String name) {				//设置名字的方法
		this.name = name;
	}

	public String getName() {						//获取名字的方法
		return name;
	}

	public void setClassRoom(String classRoom) {	//设置教室的方法
		this.classRoom = classRoom;
	}

	public String getClassRoom() {					//获取教室的方法
		return classRoom;
	}

	public abstract void teach();					//讲课的抽象方法
}

class BaseTeacher extends Teacher {					//基础班老师
	public BaseTeacher(){}

	public BaseTeacher(String name, String classRoom) {
		super(name,classRoom);
	}

	@Override
	public void teach() {
		System.out.println(getName() + "老师在" + getClassRoom() + "讲课,讲的是javase");
	}
}

