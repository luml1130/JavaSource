package com.gof._2_behavior._8_state;

/**
 * @author luml
 * @description
 * @date 2026/7/6
 */
public class StatePatternDemo {
    //客户端使用
    public static void main(String[] args) {
        Light light = new Light();

        light.toggleSwitch(); // 输出: Light is turned ON
        light.toggleSwitch(); // 输出: Light is turned OFF
        light.toggleSwitch(); // 输出: Light is turned ON
    }
}
// 抽象状态接口
 interface State {
    void toggleSwitch(Light light);
}
// 具体状态：关闭状态
 class OffState implements State {
    @Override
    public void toggleSwitch(Light light) {
        System.out.println("Light is turned ON");
        // 切换到开启状态
        light.setState(new OnState());
    }
}
// 具体状态：开启状态
 class OnState implements State {
    @Override
    public void toggleSwitch(Light light) {
        System.out.println("Light is turned OFF");
        // 切换到关闭状态
        light.setState(new OffState());
    }
}

// 上下文类：电灯
 class Light {
    private State state;

    public Light() {
        // 初始状态为关闭
        this.state = new OffState();
    }

    public void setState(State state) {
        this.state = state;
    }

    // 客户端调用此方法，无需关心内部状态逻辑
    public void toggleSwitch() {
        state.toggleSwitch(this);
    }
}
