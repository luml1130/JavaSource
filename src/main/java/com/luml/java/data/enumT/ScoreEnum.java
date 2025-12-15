package com.luml.java.data.enumT;

import com.luml.java.keyword.SwitchTest;
import org.springframework.util.StringUtils;

public enum ScoreEnum implements Printable{

    A("A"),
    B("B"),
    C("C"),
    D("D");
    private String desc;

    ScoreEnum(String a) {
    }

    public static String getDescByValue(Integer score) {
        if (score < 70) {
            return D.desc;
        }else if(score < 80 && score >= 70){
            return C.desc;
        }else if (score < 90 && score >= 80){
            return B.desc;
        }else if((score >= 90)){
            return A.desc;
        }
        /*switch () {
            case (score < 70):
                return D.desc;
            case (score < 80 && score >= 70):
                return C.desc;
            case (score < 90 && score >= 80):
                return B.desc;
            case (score >= 90):
                return A.desc;
            default:
                return D.desc;
        }*/
        return D.desc;

    }

    @Override
    public void print() {
        System.out.println("desc: " + this);
    }
}
