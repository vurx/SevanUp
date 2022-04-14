package com.vvvv.sevanUp.study.algorithm.easy;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 *  Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class N53_MaximumSubarray {

    static int go(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(pre, maxAns);
        }
        return maxAns;
    }

    // 2  pre=2 maxAns=2
    // -1 pre=1 maxAns=2
    // 3

    public static void main(String[] args) {
        System.out.println(go(new int[]{2, -1, 3}));
    }
}
