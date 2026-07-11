package com.gof._2_behavior._1_Responsibility.ChainofResponsibility;

/**
 * @author luml
 * @description 测试
 * @date 2020/4/30 16:02
 */
public class ResponsibilityTest {
    public static void main(String[] args) {
        // null;//LeaveRequest.builder().leaveDays(20).name("小明").build();
        LeaveRequest request = new LeaveRequest.Builder(20,"小明").build();

        AbstractLeaveHandler directLeaderLeaveHandler = new DirectLeaderLeaveHandler("县令");
        DeptManagerLeaveHandler deptManagerLeaveHandler = new DeptManagerLeaveHandler("知府");
        GManagerLeaveHandler gManagerLeaveHandler = new GManagerLeaveHandler("京兆尹");

        directLeaderLeaveHandler.setNextHandler(deptManagerLeaveHandler);
        deptManagerLeaveHandler.setNextHandler(gManagerLeaveHandler);

        directLeaderLeaveHandler.handlerRequest(request);
        /**
         * 20天，运行输出： 总经理:京兆尹,已经处理;流程结束。
         * 1天，运行输出： 直接主管:县令,已经处理;流程结束。
         * 3天，运行输出： 部门经理:知府,已经处理;流程结束。
         * 35天，运行输出： 审批拒绝！
         */
    }
}
