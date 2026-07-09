package com.gof._1_create.builder;

/**
 * @author luml
 * @description
 * @date 2026/7/7
 */
// 1. 产品类
public class Computer {
    private String cpu;
    private String ram;
    private String disk;

    public void setCpu(String cpu) { this.cpu = cpu; }
    public void setRam(String ram) { this.ram = ram; }
    public void setDisk(String disk) { this.disk = disk; }

    @Override
    public String toString() {
        return "Computer{cpu='" + cpu + "', ram='" + ram + "', disk='" + disk + "'}";
    }
}

// 2. 抽象建造者
interface ComputerBuilder {
    void buildCpu();
    void buildRam();
    void buildDisk();
    Computer getComputer();
}

// 3. 具体建造者
class HighEndComputerBuilder implements ComputerBuilder {
    private Computer computer = new Computer();

    @Override
    public void buildCpu() { computer.setCpu("i9-13900K"); }
    @Override
    public void buildRam() { computer.setRam("64GB DDR5"); }
    @Override
    public void buildDisk() { computer.setDisk("2TB NVMe SSD"); }
    @Override
    public Computer getComputer() { return computer; }
}

// 4. 指挥者
class Director {
    public Computer construct(ComputerBuilder builder) {
        builder.buildCpu();
        builder.buildRam();
        builder.buildDisk();
        return builder.getComputer();
    }
}
// 使用
class Main {
    public static void main(String[] args) {
        Director director = new Director();
        Computer highEnd = director.construct(new HighEndComputerBuilder());
        System.out.println(highEnd);
    }
}
