package com.luml.java.javaclass.studyD10;

class Demo3_Extends {
	public static void main(String[] args) {
		DayOne d = new DayOne();
		d.泡妞();
		d.飚车();
	}
}

class 双桨 {
	public void 泡妞() {
		System.out.println("唱红歌,厚脸皮,搞定林夕合鸟女士");
	}
}

class DayOne extends 双桨 {
	@Override
	public void 泡妞() {
		System.out.println("霸王硬上弓");
	}

	public void 飚车() {
		System.out.println("我爸是双奖");
	}
}
