package com.luml.java.javaclass.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author luml
 * @description
 * @date 2021/1/25 5:07 下午
 */
public class StringReplace {

    private static final int SIZE = 6;
    private static final String SYMBOL = "*";

    /**
     * 敏感信息脱敏实现:
     * 比如我们在点外卖的时候，外卖单子上会有我们的电话号码，平台为了保证我们的信息不被泄露，
     *                          就使用信息脱敏来将部分信息进行隐藏来达到保护我们信息的目的。
     * 陕西省西安市雁塔区xx102号-->陕西省西******x102号
     */
    @Test
    public void commonDisplay(){
        String value = "陕西省西安市雁塔区xx102号";
        if (null == value || "".equals(value)) {
            //return value;
        }
        int len = value.length();
        int pamaone = len / 2;
        int pamatwo = pamaone - 1;
        int pamathree = len % 2;
        StringBuilder stringBuilder = new StringBuilder();
        if (len <= 2) {
            if (pamathree == 1) {
                System.out.println(SYMBOL);
            }
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
        } else {
            if (pamatwo <= 0) {
                stringBuilder.append(value.substring(0, 1));
                stringBuilder.append(SYMBOL);
                stringBuilder.append(value.substring(len - 1, len));

            } else if (pamatwo >= SIZE / 2 && SIZE + 1 != len) {
                int pamafive = (len - SIZE) / 2;
                stringBuilder.append(value.substring(0, pamafive));
                for (int i = 0; i < SIZE; i++) {
                    stringBuilder.append(SYMBOL);
                }
                if ((pamathree == 0 && SIZE / 2 == 0) || (pamathree != 0 && SIZE % 2 != 0)) {
                    stringBuilder.append(value.substring(len - pamafive, len));
                } else {
                    stringBuilder.append(value.substring(len - (pamafive + 1), len));
                }
            } else {
                int pamafour = len - 2;
                stringBuilder.append(value.substring(0, 1));
                for (int i = 0; i < pamafour; i++) {
                    stringBuilder.append(SYMBOL);
                }
                stringBuilder.append(value.substring(len - 1, len));
            }
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * 手机号显示首3末4位，中间用*号隐藏代替，如：188****5593
     * @return
     */
    @Test
    public  void maskMobile() {
        String mobile = "17600186710";
        if(StringUtils.isBlank(mobile) || mobile.length() <= 8) {
            System.out.println(mobile);
        }
        System.out.println(wordMask(mobile, 3, 4, "*"));
    }

    /**
     * 电话号码显示区号及末4位，中间用*号隐藏代替，如：055****6666
     * @return
     */
    @Test
    public void maskTelephone() {
        String telephone = "05534526666";
        if(StringUtils.isBlank(telephone)) {
            System.out.println(telephone);
        }
        String result;
        if (telephone.length() > 8) {
            if (telephone.contains("-")) {
                String[] temp = telephone.split("-");
                result = temp[0] + "****" + temp[1].substring(temp[1].length() - 4, temp[1].length());
            } else {
                result = telephone.substring(0, 3) + "****" + telephone.substring(telephone.length() - 4, telephone.length());
            }
        } else {
            result = "****" + telephone.substring(telephone.length() - 4, telephone.length());
        }
        System.out.println(result);
    }

    /**
     * 身份证号显示首6末4位，中间用4个*号隐藏代替，如：340121****3754
     *          410***********4332
     * @return
     */
    @Test
    public void  maskIDCard() {
        String idCard = "410489199909094332";
        if(StringUtils.isBlank(idCard)) {
            System.out.println(idCard);
        }
        System.out.println(wordMask(idCard, 3, 4, "*"));
    }

    /**
     * 银行卡显示首6末4位，中间用4个*号隐藏代替，如：622202****4123
     * @return
     */
    @Test
    public void maskBankCard() {
        String cardNo = "6222023748343484123";
        if(StringUtils.isBlank(cardNo) || cardNo.length() < 10) {
            System.out.println(cardNo);
        }
        System.out.println(wordMask(cardNo, 6, 4, "*"));
    }

    /**
     * 邮箱像是前两位及最后一位字符，及@后邮箱域名信息，如：ch****y@163.com
     *              lu************9@163.com
     * @return
     */
    @Test
    public void  maskEmail() {
        String email = "lumengliang2009@163.com";
        if(StringUtils.isBlank(email)) {
            System.out.println(email);
        }
        String[] temp = email.split("@");
        System.out.println(wordMask(temp[0], 2, 1, "*") + "@" + temp[1]);
    }

    /**
     * 汉字掩码
     * 0-1字，如：用（用）
     * 2字，如：用于（*于）
     * 3-4字，如：用于掩（用*掩）、用于掩码（用**码）
     * 5-6字，如：用于掩码测（用于*码测）、用于掩码测试（用于**测试）
     * 大于6字，如：用于掩码测试的字符串（用于掩****字符串）
     * @return
     */
    @Test
    public void maskName() {
        String name = "用于";
        int lenth = StringUtils.length(name);
        switch (lenth) {
            case 0:
            case 1:
                System.out.println(name);
                return;
            case 2:
                System.out.println("*" + name.substring(1, 2));
                return;
            case 3:
            case 4:
                System.out.println(wordMask(name, 1, 1, "*"));
                return;
            case 5:
            case 6:
                System.out.println(wordMask(name, 2, 2, "*"));
                return;
            default:
                System.out.println(wordMask(name, 3, 3, "*"));
                return;
        }
    }

    /**
     * 全隐藏，如： ***
     * @return
     */
    @Test
    public void maskAll() {
        String str = "wewe";
        if(StringUtils.isBlank(str)) {
            System.out.println(str);
        }
        System.out.println("******");
    }

    /**
     * 对字符串进行脱敏处理 --
     * @param word 被脱敏的字符
     * @param startLength 被保留的开始长度 前余n位
     * @param endLength 被保留的结束长度 后余n位
     * @param pad 填充字符
     * */
    public static String wordMask(String word,int startLength ,int endLength,String pad)    {
        if (startLength + endLength > word.length()) {
            return StringUtils.leftPad("", word.length() - 1, pad);
        }
        String startStr = word.substring(0, startLength);
        String endStr = word.substring(word.length() - endLength, word.length());
        return startStr + StringUtils.leftPad("", word.length() - startLength - endLength, pad) + endStr;
    }
}
