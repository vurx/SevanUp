package com.vvvv.sevanUp.study.algorithm.array;

/**
 * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
 *
 * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
 */
public class N1672_RichestCustomerWealth {
    static int justDo(int[][] accounts) {
        int maxValue = 0;
        for (int i = 0; i < accounts.length; i++) {
            // 客户的总资产
            int totalMoney = 0;
            for (int money : accounts[i]) {
                totalMoney += money;
            }
            maxValue = Math.max(maxValue, totalMoney);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[][] accounts = {{1, 2, 3}, {3, 4}, {2, 2, 2, 2, 2, 2}};
        System.out.println(justDo(accounts));
    }
}
