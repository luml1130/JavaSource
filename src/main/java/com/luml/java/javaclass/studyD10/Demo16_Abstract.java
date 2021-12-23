package com.luml.java.javaclass.studyD10;

class Demo16_Abstract {
	public static void main(String[] args) {
		
	}
}

abstract class 和尚 {
	public abstract void 打坐();
	public abstract void 撞钟();
	public abstract void 念经();
	public abstract void 练武();
}

//当一个类是抽象的,他里面没有定义任何的抽象方法的应用
abstract class 天罡星 extends 和尚 {
	@Override
	public void 打坐(){}
	@Override
	public void 撞钟(){}
	@Override
	public void 念经(){}
	@Override
	public void 练武(){}
}

class 鲁智深 extends 天罡星 {
	@Override
	public void 练武() {
		System.out.println("大闹野猪林");
	}
}