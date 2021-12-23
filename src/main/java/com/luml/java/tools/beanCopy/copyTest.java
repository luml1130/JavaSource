package com.luml.java.tools.beanCopy;

//import org.springframework.beans.BeanUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author luml
 * @description
 * @date 2021/10/20
 */
public class copyTest {
    public static void main(String[] args) {
        //import org.springframework.beans.BeanUtils;
        /*MpSchoolTemplate schoolTemplate = mpSchoolTemplateService.queryMpInfoBySchoolId(schoolId);
        MpSchoolTemplateDTO schoolTemplateDTO = new MpSchoolTemplateDTO();
        BeanUtils.copyProperties(schoolTemplate, schoolTemplateDTO);*/
        String customer = "毕业/离园";
        String className = StringUtils.join(customer, "(学前1班222)");
        System.out.println(StringUtils.isNotEmpty(customer));
        System.out.println(className);
    }

}
