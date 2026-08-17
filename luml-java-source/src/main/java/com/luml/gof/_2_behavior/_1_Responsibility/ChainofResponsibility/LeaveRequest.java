package com.luml.gof._2_behavior._1_Responsibility.ChainofResponsibility;

/**
 * @author luml
 * @description :员工提交请求类
 * @date 2020/4/30 15:55
 */
public class LeaveRequest {
    /**天数*/
    private int leaveDays;
    /**姓名*/
    private String name;

//    public LeaveRequest() {
//    }

    public LeaveRequest(Builder builder){
        this.leaveDays = builder.leaveDays;
        this.name = builder.name;
    }

    public int getLeaveDays() {
        return leaveDays;
    }
    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public static class  Builder{
        private int leaveDays;
        private String name;

        public Builder(int leaveDays, String name) {
            this.leaveDays = leaveDays;
            this.name = name;
        }
        public LeaveRequest build(){
            return new LeaveRequest(this);
        }
    }

}
