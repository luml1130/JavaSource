package com.luml.java.keyword;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luml
 * @description
 * @date 2021/10/11
 */
public class otherWord {
    public static void main(String[] args) {
        System.out.println("aa");
       // boolean hasNew = false;

        while (true){
            /*List<String> aa = new ArrayList<>();
            if(CollectionUtils.isEmpty(aa)){
                continue;
            }else{
                hasNew = true;
                System.out.println("bb");
            }*/
            List<String> notInSysIds = new ArrayList<>();// wpIdFacade.getNewIds(openIds);
            if (CollectionUtils.isEmpty(notInSysIds)) {
                //continue;
                break;
            }
            System.out.println("CC");
        }
        System.out.println("dd");
    }
}
