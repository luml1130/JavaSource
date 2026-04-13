package com.luml.java.collection.tree.vo;

/**
 * 报警统计分类
 *  进供前端展示 目前无其他作用 融合的类型 id和真实使用的类型不一致
 *  //TODO 还是要全局统一一下吧 太乱了 好几个工程都有定义 结构也不一样
 *  but 无力 推动不了这个事情
 */
public enum AlarmStatEnum {
    ALL_ALARM("0", 0,"全部类型", "","","-1", false),

    UNUSUAL_EVENT("01", 1,"业务报警","","","0", false),
    ACTIVE_SAFETY("02", 2,"司机行为事件", "","","0", false),
    DRIVER_SAFETY("03", 3,"驾驶安全事件", "","","0", false),

    ROUTE_DEVIATION_ALARM("011",11,"线路偏移", "routeDeviationCount","route_deviation_count","01",false),
    LIQUID_REDUCE_ALARM("012",12,"液位异常", "liquidReduceCount","liquid_reduce_count","01",false),
    RUN_OUT_TIME("013",13,"运行超时", "runOutTimeCount","run_out_time_count","01",false),
   // PARKING_ALARM("014",14,"停车报警", "parkingCount","01","",false),

    FATIGUE_DRIVING("1", 21,"疲劳驾驶","fatigueDrivingCount","fatigue_driving_count", "02", false),
    PHONE_CALL_COUNT("2", 22,"打电话","phoneCallCount","phone_call_count","02",false),
    SMOKE_COUNT("3", 23,"抽烟","smokeCount","smoke_count","02",false),
    DISTRACT_COUNT("4", 24,"分心驾驶","distractCount","distract_count","02",false),
    DEVIATE_SEAT_COUNT("5", 25,"偏离驾驶位","deviateSeatCount","deviate_seat_count","02",false),
    CAMERA_BLOCK_COUNT("8", 26,"摄像头遮挡","cameraBlockCount","camera_block_count","02",false),
    LOOK_PHONE_COUNT("17", 27,"看手机","lookPhoneCount","look_phone_count","02",false),

    YAWNING_COUNT("9", 31,"打哈欠","yawningCount","yawning_count","03",false),
    FRONT_COLLISION_COUNT("10", 32,"前车碰撞危险","frontCollisionCount","front_collision_count","03",false),
    LANE_DEPARTURE_COUNT("11", 33,"车道偏移","laneDepartureCount","lane_departure_count","03",false),
    FRONT_VEHICLE_PROXIMITY_COUNT("12", 34,"前车近距","frontVehicleProximityCount","front_vehicle_proximity_count","03",false),
    VEHICLE_ROLLOVER_COUNT("16", 35,"车辆侧翻","vehicleRolloverCount","vehicle_rollover_count","03",false),
    REAR_BLIND_SPOT_COUNT("22", 36,"后方盲区报警","rearBlindSpotCount","rear_blind_spot_count","03",false),
    LEFT_REAR_BLIND_SPOT_COUNT("23", 37,"左后方盲区报警","leftRearBlindSpotCount","left_rear_blind_spot_count","03",false),
    RIGHT_REAR_BLIND_SPOT_COUNT("24", 38,"右后方盲区报警","rightRearBlindSpotCount","right_rear_blind_spot_count","03",false),
    OVER_SPEED_COUNT("31", 39,"超速报警","overSpeedCount","over_speed_count","03",false);

    private String id;

    private Integer order;

    private String label;

    private String attribute;

    private String column;

    private String parentId;

    private boolean disable;


    AlarmStatEnum(String id, Integer order, String label, String attribute, String column, String parentId, boolean disable) {
        this.id = id;
        this.order = order;
        this.label = label;
        this.attribute = attribute;
        this.column = column;
        this.parentId = parentId;
        this.disable = disable;
    }

    public String getId() {
        return id;
    }

    public Integer getOrder() {
        return order;
    }

    public String getLabel() {
        return label;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getColumn() {
        return column;
    }

    public String getParentId() {
        return parentId;
    }

    public boolean isDisable() {
        return disable;
    }

    public static AlarmStatEnum getById(String id) {
        for (AlarmStatEnum alarmStatEnum : AlarmStatEnum.values()) {
            if (id.equals(alarmStatEnum.getId())) {
                return alarmStatEnum;
            }
        }
        return AlarmStatEnum.ACTIVE_SAFETY;
    }
}
