package com.gof.create.create.builder;

/**
 * @author luml
 * @description form:Java设计模式及实践
 * @date 2020/4/21 23:17
 */
public class CarBuilderDirector {
    public static void main(String[] args) {

    }
    public Car buildElectricCar(CarBuilder builder){
        builder.buildCar();
        builder.addEngine("Electric 150 kW");
        builder.addBatteries("1500 kWh");
        builder.addTransmission("Manual");
        for (int i = 0; i < 4; i++) {
            builder.addWheel("20x12x30");
        }
        builder.paint("red");
        return builder.getCar();
    }
    public Car buildHybridCar(CarBuilder builder){
        builder.buildCar();
        builder.addEngine("Electric 150 kW");
        builder.addBatteries("1500 kWh");
        builder.addTransmission("Manual");
        for (int i = 0; i < 4; i++) {
            builder.addWheel("20x12x30");
        }
        builder.paint("red");
        builder.addGasTank("1500 kWh");
        builder.addEngine("Gas 1600cc");
        return builder.getCar();
    }
}

class Car {
    // This class has properties of car like color, name, make etc
}

class CarBuilder {
    Car car;
    public void buildCar() {
        this.car = new Car();
        // add basic properties
    }
    public void addEngine(String string) {
        // TODO Auto-generated method stub
    }
    public void addBatteries(String string) {
        // TODO Auto-generated method stub
    }
    public void addTransmission(String string) {
        // TODO Auto-generated method stub
    }
    public void addWheel(String string) {
        // TODO Auto-generated method stub
    }
    public void paint(String string) {
        // TODO Auto-generated method stub
    }
    public Car getCar() {
        // TODO Auto-generated method stub
        return this.car;
    }
    public void addGasTank(String string) {
        // TODO Auto-generated method stub
    }
}
