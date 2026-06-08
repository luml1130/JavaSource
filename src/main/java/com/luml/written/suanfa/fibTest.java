package com.luml.written.suanfa;

/**
 * @author luml
 * @description
 *  斐波那契数列（带记忆化优化）
 *
 *  题目‌：计算第 N 个斐波那契数。普通递归会超时，需使用记忆化或动态规划。
 *  ‌思路‌：使用数组缓存已计算的结果，避免重复计算。
 * @date 2026/6/8
 */
public class fibTest {

    /*public int fib(int n) {
        if (n <= 1) return n;
        int[] memo = new int[n + 1];
        memo = 0;
        memo = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }*/

}
