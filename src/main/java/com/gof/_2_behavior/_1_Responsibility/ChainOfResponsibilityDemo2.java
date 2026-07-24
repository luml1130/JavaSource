package com.gof._2_behavior._1_Responsibility;

/**
 * @author luml
 * @description:以下是一个经典的‌请假审批流程‌示例，模拟不同天数的请假由不同级别的管理者审批。
 * @date 2026/7/24
 */
public class ChainOfResponsibilityDemo2 {
    //步骤 4: 客户端组装与测试
    public static void main(String[] args) {
    // 创建处理者
        Approver2 manager = new Manager2();
        Approver2 director = new Director2();
        Approver2 ceo = new CEO();

        // 组装责任链: Manager -> Director -> CEO
        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        // 测试不同天数的请假
        LeaveRequest2 request1 = new LeaveRequest2(1, "事假");
        LeaveRequest2 request2 = new LeaveRequest2(4, "病假");
        LeaveRequest2 request3 = new LeaveRequest2(10, "年假");

        System.out.println("--- 请求 1 ---");
        manager.handleRequest(request1); // 经理处理

        System.out.println("--- 请求 2 ---");
        manager.handleRequest(request2); // 总监处理

        System.out.println("--- 请求 3 ---");
        manager.handleRequest(request3); // CEO处理
    }

}
//步骤 1: 定义请求对象
class LeaveRequest2 {
    private int days;
    private String reason;

    public LeaveRequest2(int days, String reason) {
        this.days = days;
        this.reason = reason;
    }

    public int getDays() {
        return days;
    }

    public String getReason() {
        return reason;
    }
}

//步骤 2: 定义抽象处理者
abstract class Approver2 {
    protected Approver2 nextApprover;

    // 设置下一个处理者
    public void setNextApprover(Approver2 nextApprover) {
        this.nextApprover = nextApprover;
    }

    // 处理请求的方法
    public abstract void handleRequest(LeaveRequest2 request);
}
//步骤 3: 实现具体处理者  ‌经理处理器（处理2天以内的请假）‌
class Manager2 extends Approver2 {
    @Override
    public void handleRequest(LeaveRequest2 request) {
        if (request.getDays() <= 2) {
            System.out.println("经理审批通过: " + request.getReason() + ", 天数: " + request.getDays());
        } else {
            // 无法处理，传递给下一个
            if (nextApprover != null) {
                nextApprover.handleRequest(request);
            } else {
                System.out.println("无人能审批该请求");
            }
        }
    }
}
//总监处理器（处理5天以内的请假）‌
class Director2 extends Approver2 {
    @Override
    public void handleRequest(LeaveRequest2 request) {
        if (request.getDays() <= 5) {
            System.out.println("总监审批通过: " + request.getReason() + ", 天数: " + request.getDays());
        } else {
            // 无法处理，传递给下一个
            if (nextApprover != null) {
                nextApprover.handleRequest(request);
            } else {
                System.out.println("无人能审批该请求");
            }
        }
    }
}
//CEO处理器（处理所有剩余请假）‌
class CEO extends Approver2 {
    @Override
    public void handleRequest(LeaveRequest2 request) {
        // CEO可以处理所有剩余请求
        System.out.println("CEO审批通过: " + request.getReason() + ", 天数: " + request.getDays());
    }
}
