package com.gof.create.create.factory.factorymethod.book;

/**
 * 工厂方法模式是在 简单工厂模式-静态工厂模式上的改进
 * @author ASUS
 */
public class Mycode {
	public static void main(String args) {
		VehicleFactory bikeFactory = new VehicleFactory() {
			@Override
			protected Vehicle createVehicle(String size) {
			if (size.equals("small")) {
				return new MountainBike();
			}else if (size.equals("large")){
				return new CityBike();
			}
			return null;
			}
		};
		Vehicle bike= bikeFactory.orderVehicle("large", "blue");
		System.out.println("bike.color:"+bike.color);
	}
	/**
	 * bike.color:blue
	 */
}

abstract class Bike extends Vehicle{}

class MountainBike extends Bike{}

class CityBike extends Bike{}