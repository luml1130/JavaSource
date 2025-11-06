package com.luml.java.javaclass.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author luml
 * @description
 * @date 2020/3/22 18:40
 */
public class DateTest {
    public static  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(sdf.format(System.currentTimeMillis()));
    }
    public static void main2(String[] args) {
        // Date d = DateUtils.addHour(new Date(),5);

        //
        //System.out.println(DateUtils.getFrontStartOfDay(-3));
        //System.out.println(DateUtils.convert2String(DateUtils.getFrontStartOfDay(-3),"yyyy-MM-dd HH:mm:ss"));
        // System.out.println(getMonthAge(20171128L,1608220800000L));
        Long startTime = 1609084800000L;
        Long endTime = 1609430400000L;
        getAttendanceDate(startTime, endTime);

       /* String bb = "1409328000000";
       int[] aa = getAgeAndMonthAgeByLongBirth(Long.parseLong(bb));
        System.out.println(aa[0]);
        System.out.println(aa[1]);*/

       /* String aa = "1447862400000";
        int mouthage = getMonthAge(Long.parseLong(aa),1608220800000L);
        Float CorporeityAge = getCorporeityAge(Long.parseLong(aa),1608220800000L);
        System.out.println(longToDate(Long.parseLong(aa)));
        System.out.println(longToDate(1608220800000L));
        System.out.println(mouthage);
        System.out.println(CorporeityAge);
        System.out.println(getAgeStr(mouthage));*/
    }

    @Test
    public void currentTime(){
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(System.currentTimeMillis());
        //1.通过Util包中的Date获取
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        //2.通过Util包的Calendar 获取
        Calendar calendar= Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));
        //3.通过Util包的Calendar 获取时间，分别获取年月日时分秒
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE);
        int h=cal.get(Calendar.HOUR_OF_DAY);
        int mi=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);
        System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");
    }


    public static Long[] getAttendanceDate(Long startTime, Long endTime) {
        int day = getDateCount(startTime,endTime);
        Long[] dateArray = new Long[day];
        for (int i = 0; i < day; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(startTime);
            calendar.add(calendar.DATE,i);
            calendar.set(Calendar.HOUR_OF_DAY, 12);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateArray[i] = calendar.getTimeInMillis();
//            System.out.println(convert2String(calendar.getTimeInMillis(),TIME_FORMAT));
        }
        return dateArray;
    }

    public static int getDateCount(Long startDate, Long endDate) {
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis(startDate);
        start.set(Calendar.HOUR_OF_DAY, 23);
        start.set(Calendar.MINUTE, 59);
        start.set(Calendar.SECOND, 59);
        start.set(Calendar.MILLISECOND, 0);
        Calendar end = Calendar.getInstance();
        end.setTimeInMillis(endDate);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 0);
        Long t = end.getTimeInMillis() - start.getTimeInMillis();
        Long count = t / (1000 * 60 * 60 * 24);
        return count.intValue() + 1;
    }

    public static int[] getAgeAndMonthAgeByLongBirth(long birth) {
        int[] ageVal = new int[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String bir = convert2String(birth, "yyyy-MM-dd");
        Date birthday = null;
        try {    //将生日 转为  日期型
            if (bir != null && !"".equals(bir)) {
                birthday = sdf.parse(bir);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d = new Date();//系统当前时间
        //用这两个时间来计算  孩子的月龄和年龄
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        if (birthday != null) {
            c1.setTime(birthday);
            c2.setTime(d);
            int monthAge = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
            int age = Integer.parseInt((monthAge / 12) + "");
            ageVal[0] = age;
            ageVal[1] = monthAge;
        } else {
            ageVal[0] = 0;
            ageVal[1] = 0;
        }
        return ageVal;
    }

    public static String convert2String(Long time, String format) {
        if (time != null) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    /**
     * 获得年龄
     * 23个月算1算一岁哦
     * current date:2021 08 05
     * 1530460800000：2018-07-02---现在 37个月 3岁
     * 1535990400000：2018-09-04---现在 35个月 2岁
     */
    @Test
    public  void  getYearAge() {

        Long birthDay = Long.parseLong("1521269872926");
        Long currentTime = System.currentTimeMillis();
        if (birthDay == null || birthDay == 0 || currentTime <= birthDay) {
            return;
           // return 0;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(birthDay);
        c2.setTimeInMillis(currentTime);

        int monthAge = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        System.out.println(monthAge);

        int age = Integer.parseInt((monthAge / 12) + "");
        System.out.println(age);
        //return monthAge;
    }

    /**
     * 获取倒计日的月龄
     */
    @Test
    public  void  getMonthAgeV2() {
        Long birthDay =  Long.parseLong("1535990400000");
        if (birthDay == null || birthDay == 0) {
            return ;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTimeInMillis(birthDay);
        c2.setTime(new Date());
        int monthAge = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + (c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        System.out.println(monthAge);
        //return monthAge;
    }

    public static float getCorporeityAge(Long birthTime, Long evalDate) {
        float age = 3.0f;
        if(birthTime == null || birthTime == 0){
            return 3.0f;
        }
        Calendar calCurrent = Calendar.getInstance();
        calCurrent.setTimeInMillis(evalDate);
        int currentYear = calCurrent.get(Calendar.YEAR);
        int currentMonth = calCurrent.get(Calendar.MONTH) + 1;
        int currentDay = calCurrent.get(Calendar.DAY_OF_MONTH);

        Calendar calBirth = Calendar.getInstance();
        calBirth.setTimeInMillis(birthTime);
        int birthYear = calBirth.get(Calendar.YEAR);
        int birthMonth = calBirth.get(Calendar.MONTH) + 1;
        int birthDay = calBirth.get(Calendar.DAY_OF_MONTH);

        //年龄小于3岁,按3岁计算
        if (currentYear - birthYear < 3) {
            return 3.0f;
        }
        if (currentYear - birthYear == 3) {
            if (currentMonth < birthMonth) {
                return 3.0f;
            }
            if (currentMonth == birthMonth) {
                if (currentDay < birthDay) {
                    return 3.0f;
                }
            }
        }

        //年龄大于6岁,按6岁计算
        boolean sixYearsOLd = false;
        if (currentYear - birthYear > 6) {
            //大于6岁
            return 6.0f;
        }
        if (currentYear - birthYear == 6) {
            sixYearsOLd = true;
            if (currentMonth > birthMonth) {
                //大于6岁
                return 6.0f;
            }
            if (currentMonth == birthMonth) {
                if (currentDay > birthDay) {
                    //大于6岁
                    return 6.0f;
                }
            }
        }
        //计算3-5岁生日--------------------------------------
        if (!sixYearsOLd) {
            //当前月>出生月
            if (currentMonth > birthMonth) {
                //已过生日
                if (currentMonth - birthMonth > 6) {
                    //已过生日,且超过6个月  年龄＝测试年－出生年＋0.5
                    return currentYear - birthYear + 0.5f;
                } else if (currentMonth - birthMonth == 6) {
                    if (currentDay >= birthDay) {
                        //已过生日,且超过6个月  年龄＝测试年－出生年＋0.5
                        return currentYear - birthYear + 0.5f;
                    } else {
                        //已过生日,且不满6个月  年龄＝测试年－出生年
                        return currentYear - birthYear;
                    }
                } else {
                    //已过生日,且不满6个月  年龄＝测试年－出生年
                    return currentYear - birthYear;
                }
            }
            //当前月==出生月
            if (currentMonth == birthMonth) {
                if (currentDay >= birthDay) {
                    //已过生日,且不满6个月  年龄＝测试年－出生年
                    return currentYear - birthYear;
                } else {
                    //未过生日,且距生日6个月以下  年龄＝测试年－出生年－0.5
                    return currentYear - birthYear - 0.5f;
                }
            }
            //当前月<出生月
            if (currentMonth < birthMonth) {
                if (birthMonth - currentMonth > 6) {
                    //未过生日,且距生日6个月以上  年龄＝测试年－出生年－1
                    return currentYear - birthYear - 1;
                } else if (birthMonth - currentMonth == 6) {
                    if (birthDay > currentDay) {
                        //未过生日,且距生日6个月以上  年龄＝测试年－出生年－1
                        return currentYear - birthYear - 1;
                    } else {
                        //未过return 生日,且距生日6个月以下  年龄＝测试年－出生年－0.5
                        return currentYear - birthYear - 0.5f;
                    }
                } else {
                    //未过生日,且距生日6个月以下  年龄＝测试年－出生年－0.5
                    return currentYear - birthYear - 0.5f;
                }
            }
        }
        //计算6岁生日--------------------------------------
        if (sixYearsOLd) {
            //当前月>出生月
            if (currentMonth > birthMonth) {
                //已过生日  年龄＝测试年－出生年
                return currentYear - birthYear;
            }
            //当前月==出生月
            if (currentMonth == birthMonth) {
                if (currentDay >= birthDay) {
                    //已过生日  年龄＝测试年－出生年
                    return currentYear - birthYear;
                } else {
                    //未过生日  年龄＝测试年－出生年－1
                    return currentYear - birthYear - 1;
                }
            }
            //当前月<出生月
            if (currentMonth < birthMonth) {
                //未过生日  年龄＝测试年－出生年－1
                return currentYear - birthYear - 1;
            }
        }
        return age;
    }

    public static String getAgeStr(Integer monthAge) {
        String ageStr = "";
        if (monthAge == null) {
            return "";
        }
        if (monthAge > 0 && monthAge < 12) {
            ageStr = monthAge + "/12";
        }
        if (monthAge >= 12) {
            int year = monthAge / 12;
            int month = monthAge % 12;
            if (month > 0) {
                ageStr = year + " " + month + "/12";
            } else {
                ageStr = year + "";
            }
        }
        return ageStr;
    }


    public static void main1(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date e = new Date();
        System.out.println(e.getTime());
        /*Date date = new Date();
		System.out.println(sdf.format(date));

		Date date1 = new Date(System.currentTimeMillis()+1000*60*60*24*10);
		int num = date1.compareTo(date);
		System.out.println(num);*/
        System.out.println(longToDate(1571916920759L));
    }

    /**
     * long 类型转换成日期
     */
    public static String longToDate(Long longTime){
        Date date = new Date(longTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
}
