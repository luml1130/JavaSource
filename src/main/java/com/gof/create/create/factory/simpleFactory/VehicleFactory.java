package com.gof.create.create.factory.simpleFactory;

/**
 * @author ASUS
 */
public class VehicleFactory {

	public static void main(String[] args) {
		create(VehicleType.Bike);
	}
	
	public enum VehicleType{
		Bike,Car,Truck
	}
	
	public static Vehicle create(VehicleType type){
		if (type.equals(VehicleType.Bike)) {
			return new Bike();
		}else if (type.equals(VehicleType.Car)){
			return new Car();
		}else if (type.equals(VehicleType.Truck)) {
			return new Truck();
		}else {
			return null;
		}
	}
}

abstract class Vehicle {
    //abstract Vehicle class
}

class Bike extends Vehicle{
	//Bike implementation of vehicle
}

class Car extends Vehicle{
	//Car implementation of vehicle
}

class Truck extends Vehicle{
	//Bike implementation of vehicle
}