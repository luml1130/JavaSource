package com.luml.java.data.json.fastjson.Serializer.e6yun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author luml
 * @description
 * @date 2025/11/1
 */
@Data
public class E6YunEnumSerialTest01 {

    private Integer id;

    @JSONField(serializeUsing = TmsBaseEnumCodec.class, deserializeUsing = TmsBaseEnumCodec.class)
    private WaybillVehicleSourceEnum vehicleSource;


    public static void main(String[] args) {
        E6YunEnumSerialTest01 test01 = new E6YunEnumSerialTest01();
        test01.setId(1);
        test01.setVehicleSource(WaybillVehicleSourceEnum.YMM);

        System.out.println(JSON.toJSONString(test01));
        /**
         * {"id":1,"vehicleSource":2, "vehicleSourceLabel": "运满满"}
         */

        String test01String = "{\"id\":1,\"vehicleSource\":2, \"vehicleSourceLabel\": \"运满满\"}";
        E6YunEnumSerialTest01 test011Object =  JSON.parseObject(test01String,E6YunEnumSerialTest01.class);

        System.out.println(test011Object.getVehicleSource().getCode());
        System.out.println(test011Object.getVehicleSource().getLabel());
    }
}
