package com.luml.java.javaclass.studyD10;

class Demo17_Abstract {
	public static void main(String[] args) {
		林平之 小林子 = new 林平之();
		小林子.自宫();
	}
}

abstract class 葵花宝典 {
	public abstract void 自宫();
}

class 林平之 extends 葵花宝典 {
	@Override
	public void 自宫() {
		System.out.println("用指甲刀");
	}
}

class 岳不群 extends 葵花宝典 {
	@Override
	public void 自宫() {
		System.out.println("用钳子");
	}
}

class 刘瑾 extends 葵花宝典 {
	@Override
	public void 自宫() {
		System.out.println("用锤子,不忍直视");
	}
}
