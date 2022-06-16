package com.vvvv.sevanUp.study.algorithm.array;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 * <p>
 * Example 1:
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class N485_MaxConsecutiveOnes {


    static int findMaxConsecutiveOnes1(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 0, 1, 1, 1, 1, 0, 1};
        System.out.println(findMaxConsecutiveOnes1(nums));
    }

    /**
     * Time complexity O(N)
     */
    static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, n = 0;
        for (int num : nums) {
            if (num == 1) {
                n++;
            } else {
                max = Math.max(max, n);
                n = 0;
            }
        }
        max = Math.max(max, n);
        return max;
    }


}
