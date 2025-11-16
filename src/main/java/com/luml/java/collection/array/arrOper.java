package com.luml.java.collection.array;

/**
 * @author luml
 * @description
 * @date 2020/11/23
 */
public class arrOper {

    public static void main(String[] args) {
        String[][] eval = new String[1][2];
        eval[0][0] = "1";
        //eval[0][1] = "2";

        System.out.println(eval[0][0]);
        System.out.println(eval[0][1]);

        String[][] eval2 = intakeRangeDic("energy");
        String format = String.format("（%s%%~%s%%）", eval2[0][0], eval2[0][1]);
        System.out.println(format);
    }

    public void oper(){
    }


    public static String[][] intakeRangeDic(String nutrientId){
        String[][] rangeStr = new String[1][2];
        switch (nutrientId){
            case "energy":
                rangeStr[0][0] = "85";
                rangeStr[0][1] = "200";
                break;
            case "protein":
            case "fat":
            case "carbohydrate":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "200";
                break;
            case "va":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "340";
                break;
            case "vb1":
            case "vb2":
            case "vb3":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "5700";
                break;
            case "vc":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "1650";
                break;
            case "ve":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "5620";
                break;
            case "ca":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "250";
                break;
            case "fe":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "300";
                break;
            case "zn":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "230";
                break;
            case "se":
                rangeStr[0][0] = "80";
                rangeStr[0][1] = "740";
                break;
        }
        return rangeStr;
    }
}
