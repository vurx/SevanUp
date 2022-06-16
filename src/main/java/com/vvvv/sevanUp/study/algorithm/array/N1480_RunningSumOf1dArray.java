package com.vvvv.sevanUp.study.algorithm.array;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,6,10]
 * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 */
public class N1480_RunningSumOf1dArray {
    static void justDo(int[] num) {
        int[] result = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            if (i == 0) {
                result[i] = num[i];
            } else {
                result[i] = result[i - 1] + num[i];
            }
        }
    }

    public static void main(String[] args) {
        int[] a ={1,2,3,4};
        justDo(a);
    }

    /**
     * 通过观察和思考可得到：
     *
     * 最终答案中，第1个值不用变，第n个值是参数中数组第1到第n个值的和
     * 不用新开一个数组，直接在原数组中操作即可
     * 只用一次遍历，要得到第n个值，只需要用第n-1个值加上第n个值即可
     *
     */
    class Solution {
        public int[] runningSum(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }
            return nums;
        }
    }
}
