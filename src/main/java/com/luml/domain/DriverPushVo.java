package com.luml.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author luml
 * @description 同步推送Vo
 * @date 2025/11/13
 */
@Data
@Builder
public class DriverPushVo {

    private Integer driverId;

    private String name;

    private String phone;

    private String oldPhone;

    private Boolean isUpdatePhone = false;

    public DriverPushVo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public DriverPushVo(Integer driverId, String name, String phone) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
    }

    public DriverPushVo(Integer driverId, String name, String phone, String oldPhone) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
        this.oldPhone = oldPhone;
    }

    public DriverPushVo(Integer driverId, String name, String phone, String oldPhone, Boolean isUpdatePhone) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
        this.oldPhone = oldPhone;
        this.isUpdatePhone = isUpdatePhone;
    }
}
