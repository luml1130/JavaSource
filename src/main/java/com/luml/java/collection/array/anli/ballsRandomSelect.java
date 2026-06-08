package com.luml.java.collection.array.anli;

import java.util.Arrays;
import java.util.Random;

/**
 * @author luml
 * @description
 *  演示随机抽取算法机选双色球
 * @date 2026/6/8
 */
public class ballsRandomSelect {

    public static void main(String[] args) {
        String[] redBalls = ballsRandomSelect();
        Arrays.sort(redBalls);
        String[] poor =
                {"01","02","03","04","05","06","07","08","09","10",
                        "11","12","13","14","15","16","17","18","19","20",
                        "21","22","23","24","25","26","27","28","29","30",
                        "31","32","33"};
        //随机获取篮球号码
        Random r = new Random();
        int index = r.nextInt(16);
        String blueBall = poor[index];
        //定义一个数组存储所有号码
        String[] balls = new String[7];
        //将随机获取的一组红球号码复制到balls中
        System.arraycopy(redBalls,0,balls,0,redBalls.length);
        balls[6] = blueBall;
        System.out.println(Arrays.toString(balls));
        /**
         * [04, 06, 12, 19, 21, 25, 09] 每次不一样
         * [03, 08, 09, 17, 19, 24, 11]
         */
    }

    public static String[] ballsRandomSelect(){
        String[] poor =
                {"01","02","03","04","05","06","07","08","09","10",
                        "11","12","13","14","15","16","17","18","19","20",
                        "21","22","23","24","25","26","27","28","29","30",
                        "31","32","33"};
        boolean[] flags = new boolean[poor.length];
        String[] balls = new String[6];
        Random r = new Random();
        int i = 0;
        for(;;){
            int index = r.nextInt(poor.length);
            if(!flags[index]){
                balls[i++] = poor[index];
                flags[index] = true;
            }
            if(i==6){
                break;
            }
        }
        return balls;
    }
}
