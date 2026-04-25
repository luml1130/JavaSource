package com.luml.sence.rule.QlExpress.g7e6;

import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeAddressNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeBankCardNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeCarNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeCertificateValidityOrExpirationDate;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeContractNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeDriverLicenseNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeEmail;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeIdentificationNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeOthers;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizePersonalName;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizePhoneNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeVehicleEngineNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeVehicleIdentificationNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.DesensitizeWechatOrAlipayNumber;
import com.luml.sence.rule.QlExpress.g7e6.format.FormatUpdateColumn;
import com.luml.sence.rule.QlExpress.g7e6.format.special.DesensitizeDeliverymanList;
import com.luml.sence.rule.QlExpress.g7e6.format.special.DesensitizeMainDriver;
import com.luml.sence.rule.QlExpress.g7e6.format.special.DesensitizeOtherCommonDrivers;
import com.luml.sence.rule.QlExpress.g7e6.format.special.DesensitizePersonalNameList;
import com.luml.sence.rule.QlExpress.g7e6.format.special.DesensitizeVehicleNumberList;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiqiang@e6yun.com
 * @date 2022/8/10
 */
@Slf4j
public class ExpressEngine {

    private ExpressEngine() { }

    public static ExpressRunner buildCheckRunner() {
        ExpressRunner runner = buildExpressRunner();

        // 字符串长度


        return runner;
    }


    /**
     * 格式化数据引擎
     * @return {@link ExpressRunner} 加入格式化函数
     */
    public static ExpressRunner buildFormatRunner() {
        ExpressRunner runner = buildExpressRunner();

        // 更新JSON字段
        runner.addFunction(FormatUpdateColumn.NAME, new FormatUpdateColumn());

        // 脱敏函数
        runner.addFunction(DesensitizeAddressNumber.OPERATOR_NAME, new DesensitizeAddressNumber());
        runner.addFunction(DesensitizeBankCardNumber.OPERATOR_NAME, new DesensitizeBankCardNumber());
        runner.addFunction(DesensitizeCarNumber.OPERATOR_NAME, new DesensitizeCarNumber());
        runner.addFunction(DesensitizeCertificateValidityOrExpirationDate.OPERATOR_NAME, new DesensitizeCertificateValidityOrExpirationDate());
        runner.addFunction(DesensitizeContractNumber.OPERATOR_NAME, new DesensitizeContractNumber());
        runner.addFunction(DesensitizeDriverLicenseNumber.OPERATOR_NAME, new DesensitizeDriverLicenseNumber());
        runner.addFunction(DesensitizeEmail.OPERATOR_NAME, new DesensitizeEmail());
        runner.addFunction(DesensitizeIdentificationNumber.OPERATOR_NAME, new DesensitizeIdentificationNumber());
        runner.addFunction(DesensitizeOthers.OPERATOR_NAME, new DesensitizeOthers());
        runner.addFunction(DesensitizePersonalName.OPERATOR_NAME, new DesensitizePersonalName());
        runner.addFunction(DesensitizePhoneNumber.OPERATOR_NAME, new DesensitizePhoneNumber());
        runner.addFunction(DesensitizeVehicleEngineNumber.OPERATOR_NAME, new DesensitizeVehicleEngineNumber());
        runner.addFunction(DesensitizeVehicleIdentificationNumber.OPERATOR_NAME, new DesensitizeVehicleIdentificationNumber());
        runner.addFunction(DesensitizeWechatOrAlipayNumber.OPERATOR_NAME, new DesensitizeWechatOrAlipayNumber());

        runner.addFunction(DesensitizeVehicleNumberList.OPERATOR_NAME, new DesensitizeVehicleNumberList());
        runner.addFunction(DesensitizeMainDriver.OPERATOR_NAME, new DesensitizeMainDriver());
        runner.addFunction(DesensitizeDeliverymanList.OPERATOR_NAME, new DesensitizeDeliverymanList());
        runner.addFunction(DesensitizeOtherCommonDrivers.OPERATOR_NAME, new DesensitizeOtherCommonDrivers());
        runner.addFunction(DesensitizePersonalNameList.OPERATOR_NAME, new DesensitizePersonalNameList());


        return runner;
    }

    /**
     * 构建基础的规则引擎
     * @return  {@link ExpressRunner}
     */
    private static ExpressRunner buildExpressRunner() {
        ExpressRunner runner = new ExpressRunner(true, log.isDebugEnabled());
        // 短路与或
        runner.setShortCircuit(true);
        // 白名单
        return runner;
    }
}
