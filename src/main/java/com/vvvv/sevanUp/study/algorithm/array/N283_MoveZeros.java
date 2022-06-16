package com.vvvv.sevanUp.study.algorithm.array;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 */
public class N283_MoveZeros {

    static int[] moveZeroes1(int[] nums) {
        
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 3, 0, 4, 5, 6, 0, 7};
        System.out.println(Arrays.toString(moveZeroes1(nums)));
    }











    static int[] moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num !=0) {
                nums[index++] = num;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

}
