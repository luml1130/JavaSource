package com.luml.juc.lock.deadLock;

/**
 * @author luml
 * @description
 * 案例二：业务场景——银行转账死锁
 * 在实际业务中，死锁常发生在需要同时操作多个资源（如两个账户）的场景。如果加锁顺序依赖于参数传入的顺序，极易发生死锁。
 * ‌场景描述：‌
 *     ‌账户 A 转给 账户 B‌：线程 1 先锁定 A，再锁定 B。
 *     ‌账户 B 转给 账户 A‌：线程 2 先锁定 B，再锁定 A。
 *     ‌结果‌：若两个转账同时发生，线程 1 持有 A 锁等待 B 锁，线程 2 持有 B 锁等待 A 锁，导致死锁。
 * 解决方案提示：‌ 可以通过比较账户的唯一 ID（如哈希码或自定义 ID），规定始终先锁定 ID 较小的账户，再锁定 ID 较大的账户，从而统一加锁顺序。
 * @date 2026/7/10
 */
public class BankTransferDeadlock {
    static class Account {
        private double balance;
        private final String name;

        public Account(String name, double balance) {
            this.name = name;
            this.balance = balance;
        }

        public void debit(double amount) { balance -= amount; }
        public void credit(double amount) { balance += amount; }
        public String getName() { return name; }
    }

    // 错误的转账方法：锁的顺序取决于参数顺序
    public static void transfer(Account from, Account to, double amount) {
        synchronized (from) {
            synchronized (to) {
                if (from.balance >= amount) {
                    from.debit(amount);
                    to.credit(amount);
                    System.out.println("成功转账: " + amount + " 从 " + from.getName() + " 到 " + to.getName());
                } else {
                    System.out.println("余额不足");
                }
            }
        }
    }

    public static void main(String[] args) {
        Account accountA = new Account("AccountA", 1000);
        Account accountB = new Account("AccountB", 1000);

        // 线程1：A -> B
        new Thread(() -> transfer(accountA, accountB, 100), "Transfer-A-to-B").start();
        // 线程2：B -> A
        new Thread(() -> transfer(accountB, accountA, 100), "Transfer-B-to-A").start();
    }
}
