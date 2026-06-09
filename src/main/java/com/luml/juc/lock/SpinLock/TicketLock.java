package com.luml.juc.lock.SpinLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luml
 * @description
 * @date 2026/6/9
 */
public class TicketLock {
    /**
     * 每次都要查询一个serviceNum 服务号，影响性能（必须要到主内存读取，并阻止其他cpu修改）。
     */
    private AtomicInteger serviceNum = new AtomicInteger();
    private AtomicInteger      ticketNum  = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCAL      = new ThreadLocal<Integer>();
    public void lock() {
        int myticket = ticketNum.getAndIncrement();
        LOCAL.set(myticket);
        while (myticket != serviceNum.get()) {
        }
    }
    public void unlock() {
        int myticket = LOCAL.get();
        serviceNum.compareAndSet(myticket, myticket + 1);
    }
}
