package com.gof._2_behavior._1_Responsibility.ChainofResponsibility;

/**
 * @author luml
 * @description 部门经理处理类
 * @date 2020/4/30 16:01
 */
public class DeptManagerLeaveHandler extends AbstractLeaveHandler  {
    public DeptManagerLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() >this.MIN && request.getLeaveDays() <= this.MIDDLE){
            System.out.println("部门经理:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}
