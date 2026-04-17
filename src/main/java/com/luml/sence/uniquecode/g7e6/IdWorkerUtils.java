package com.luml.sence.uniquecode.g7e6;

//import com.e6yun.project.common.redis.E6RedisService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author houjun@e6yun.com
 * @Description
 * @Date 2020/8/1 17:26
 */
@SuppressWarnings("unused")
@Slf4j
//@Component
public class IdWorkerUtils {

    //@Resource
   // private E6RedisService redisService;

   // @Value("${spring.application.name:default}")
    private String appName;


    private static final Logger logger = LoggerFactory.getLogger(IdWorkerUtils.class);

    /**
     * 开始时间截 (2017-03-10 10:06:50)
     */
    private static final long twepoch = 1489111610226L;

    /**
     * 机器id所占的位数
     */
    private static final long workerIdBits = 10L;

    /**
     * 支持的最大机器id，1023
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 序列在id中占的位数
     */
    private static final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private static final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移22位(12+10)
     */
    private static final long timestampLeftShift  = sequenceBits + workerIdBits;


    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 工作机器ID(0~1024)
     */
    private long workerId;


    private static IdWorkerUtils idWorkerUtils;


    //==============================Constructors=====================================

    /**
     * 构造
     *
     */
    @PostConstruct
    void init() {
        this.workerId = getMachineNum();
        if (workerId<0 || workerId> maxWorkerId) {
            throw  new RuntimeException(String.format("IdWorkerUtils worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        logger.info("idWorkerUtils, workerId origin:{}", workerId);
        idWorkerUtils = this;
    }

    private long getMachineNum() {
        try {
            long increment = 11L; //redisService.getStringRedisTemplate().opsForValue().increment(genFeatureIdKey(), 1);
            return increment & maxWorkerId;
        } catch (Exception e) {
            throw new RuntimeException("IdWorkerUtils workerId increment error",e);
        }
    }


    String genFeatureIdKey() {
        return "snowflake-featureId".concat("-").concat(appName);
    }


    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    private synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 静态工具类
     *
     * @return
     */
    public static synchronized String generateId() {
        //return String.valueOf(idWorkerUtils.nextId()); //正常应该用这个哦 下面是java项目自测用
        return String.valueOf(new IdWorkerUtils().nextId());
    }


    // ===================================================

    /*
    第一段（38位）：当前时间 - 2015/01/01 , 38位，大约可以用到2060年
    第二段（10位）：机器码：0 ~ 2^10-1（1023）
    第三段（12位）：毫秒内随机数： 0 ~ 2^12-1（4095）

    11000011001101110010010110110000100110  101010010  100010001001

    任意时间，集群内能够产生的最大id：879174942908743679
    11000011001101110010010110110000100110  1111111111 111111111111
    任意时间，集群内能够产生的最小id: 879174942904549376
    11000011001101110010010110110000100110  0000000000 000000000000
     */

    /** 最小机器码 */
    private static final long MIN_WORKER_ID = 0L;
    /** 最大机器码： 2^10 - 1 */
    private static final long MAX_WORKER_ID = 1023L;

    /** 最小序列 */
    private static final long MIN_SEQ = 0L;
    /** 最大序列: 2^12 - 1 */
    private static final long MAX_SEQ = 4095L;


    /**
     * 根据给定的时间计算最大ID
     * 逻辑和mysql函数 time_id_max('2023-10-12 15:23:24') 一致
     * @param date 给定的时间
     * @return 最大ID
     */
    public static long findMaxId(Date date) {
        Assert.notNull(date, "时间不可为空");
        return (formatDate(date).getTime() - twepoch) << timestampLeftShift | MAX_WORKER_ID << workerIdShift | MAX_SEQ;
    }


    /**
     * 传入一个时间，获得这个时间中可以生成的最大id
     * 逻辑和mysql函数 time_id_min('2023-10-12 15:23:24') 一致
     */
    public static long findMinId(Date date) {
        Assert.notNull(date, "时间不可为空");
        return (formatDate(date).getTime() - twepoch) << timestampLeftShift | MIN_WORKER_ID << workerIdShift | MIN_SEQ;
    }

    /**
     * 格式化时间，去掉毫秒
     */
    private static Date formatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * id解析为时间
     * @param id 由本工具类生成的id
     * @return id生成时间
     */
    public static Date parseTime(String id) {
        Date date = null;
        try {
            long formattedId = Long.parseLong(id);
            long mills = (formattedId >> timestampLeftShift) + twepoch;
            date = formatDate(new Date(mills));
        } catch (Exception e) {
            log.error("解析id时遇到错误:" + id, e);
        }
        return date;
    }

//    public static void main(String[] args) {
////        String dateStr = "2024-10-17 20:32:15.251";
//        String dateStr = "2023-10-12 15:23:24.998";
//        // 日期转时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        Date date = DateUtil.parse(dateStr, sdf);
//
//        System.out.println(date.getTime());
//        System.out.println(sdf.format(date));
//
//        long maxId = findMaxId(date);
//        long minId = findMinId(date);
//
//        System.out.println(Long.toBinaryString(maxId) + " === " +maxId);
//        System.out.println(Long.toBinaryString(minId) + " === " +minId);
//
//        System.out.println("id为数：" + String.valueOf(maxId).length());
//
//        Date date1 = parseTime(String.valueOf(maxId));
//        Date date2 = parseTime(String.valueOf(minId));
//
//        System.out.println(sdf.format(date1));
//        System.out.println(sdf.format(date2));
//
//
//        Assert.isTrue(maxId > minId, "id不正确");
//        Assert.isTrue(date.compareTo(date1) == 0, "时间不一致");
//        Assert.isTrue(date.compareTo(date2) == 0, "时间不一致");
//
//
//    }

}
