package com.gof._2_behavior._1_Responsibility.ChainofResponsibility;

/**
 * @author luml
 * @description 直接主管处理类
 * @date 2020/4/30 15:57
 */
public class DirectLeaderLeaveHandler extends AbstractLeaveHandler {

    public DirectLeaderLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    protected void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() <= this.MIN){
            System.out.println("直接主管:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }
    }
}
