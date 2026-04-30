package com.gof.behavior.Responsibility;

/**
 * 责任链模式示例：请假审批系统
 * 
 * 1. Handler (抽象处理者): 定义处理请求的接口和下一个处理者的引用
 * 2. ConcreteHandler (具体处理者): 具体处理请求的逻辑，如组长、经理、总监
 * 3. Request (请求对象): 封装请求数据
 *
 * 这段代码展示了责任链模式的核心结构：
 *     ‌解耦‌：发送请求的客户端（ChainOfResponsibilityDemo）不需要知道具体哪个对象会处理请求，只需将请求发送给链的第一个节点。
 *     ‌动态组合‌：通过 setNextApprover 方法，可以灵活地改变链的顺序或增加新的处理者（如添加“HR”节点）。
 *     ‌单一职责‌：每个具体处理者（TeamLeader, Manager, Director）只关注自己能够处理的请求范围，符合开闭原则。
 *     ‌传递机制‌：如果当前节点无法处理，请求会自动传递给下一个节点，直到被处理或链结束。
 */

// 1. 定义请求对象
class LeaveRequest {
    private String employeeName;
    private int days;
    private String reason;

    public LeaveRequest(String employeeName, int days, String reason) {
        this.employeeName = employeeName;
        this.days = days;
        this.reason = reason;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getDays() {
        return days;
    }

    public String getReason() {
        return reason;
    }
}

// 2. 定义抽象处理者 (Handler)
abstract class Approver {
    protected Approver nextApprover;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    // 设置下一个处理者
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    // 处理请求的方法
    public void handleRequest(LeaveRequest request) {
        if (canHandle(request)) {
            processRequest(request);
        } else if (nextApprover != null) {
            System.out.println(name + " 无法审批，转交给下一位...");
            nextApprover.handleRequest(request);
        } else {
            System.out.println("请求未被任何处理者处理: " + request.getEmployeeName() + " 的请假申请被拒绝或需更高级别审批（无后续处理者）。");
        }
    }

    // 判断当前处理者是否能处理该请求
    protected abstract boolean canHandle(LeaveRequest request);

    // 具体处理逻辑
    protected abstract void processRequest(LeaveRequest request);
}

// 3. 具体处理者：组长 (处理 <= 3 天的请假)
class TeamLeader extends Approver {
    public TeamLeader(String name) {
        super(name);
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getDays() <= 3;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("[组长] " + name + " 批准了 " + request.getEmployeeName() + " 的 " + request.getDays() + " 天请假。原因: " + request.getReason());
    }
}

// 4. 具体处理者：经理 (处理 <= 7 天的请假)
class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getDays() <= 7;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("[经理] " + name + " 批准了 " + request.getEmployeeName() + " 的 " + request.getDays() + " 天请假。原因: " + request.getReason());
    }
}

// 5. 具体处理者：总监 (处理 <= 15 天的请假)
class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    @Override
    protected boolean canHandle(LeaveRequest request) {
        return request.getDays() <= 15;
    }

    @Override
    protected void processRequest(LeaveRequest request) {
        System.out.println("[总监] " + name + " 批准了 " + request.getEmployeeName() + " 的 " + request.getDays() + " 天请假。原因: " + request.getReason());
    }
}

// 6. 客户端测试类
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 创建处理者对象
        Approver teamLeader = new TeamLeader("张组长");
        Approver manager = new Manager("李经理");
        Approver director = new Director("王总监");

        // 构建责任链: 组长 -> 经理 -> 总监
        teamLeader.setNextApprover(manager);
        manager.setNextApprover(director);

        System.out.println("--- 场景 1: 请假 2 天 ---");
        LeaveRequest request1 = new LeaveRequest("员工A", 2, "事假");
        teamLeader.handleRequest(request1);

        System.out.println("\n--- 场景 2: 请假 5 天 ---");
        LeaveRequest request2 = new LeaveRequest("员工B", 5, "病假");
        teamLeader.handleRequest(request2);

        System.out.println("\n--- 场景 3: 请假 10 天 ---");
        LeaveRequest request3 = new LeaveRequest("员工C", 10, "年假");
        teamLeader.handleRequest(request3);

        System.out.println("\n--- 场景 4: 请假 20 天 (超出链条处理能力) ---");
        LeaveRequest request4 = new LeaveRequest("员工D", 20, "长期休假");
        teamLeader.handleRequest(request4);

    }
    /**
     * --- 场景 1: 请假 2 天 ---
     * [组长] 张组长 批准了 员工A 的 2 天请假。原因: 事假
     *
     * --- 场景 2: 请假 5 天 ---
     * 张组长 无法审批，转交给下一位...
     * [经理] 李经理 批准了 员工B 的 5 天请假。原因: 病假
     *
     * --- 场景 3: 请假 10 天 ---
     * 张组长 无法审批，转交给下一位...
     * 李经理 无法审批，转交给下一位...
     * [总监] 王总监 批准了 员工C 的 10 天请假。原因: 年假
     *
     * --- 场景 4: 请假 20 天 (超出链条处理能力) ---
     * 张组长 无法审批，转交给下一位...
     * 李经理 无法审批，转交给下一位...
     * 请求未被任何处理者处理: 员工D 的请假申请被拒绝或需更高级别审批（无后续处理者）。
     */
}
