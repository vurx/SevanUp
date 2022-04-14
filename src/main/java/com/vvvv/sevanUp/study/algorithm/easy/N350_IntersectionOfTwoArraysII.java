package com.vvvv.sevanUp.study.algorithm.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Leetcode-easy-350
 * <p>
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must
 * appear as many times as it shows in both arrays and you may return the result in any order.
 * <p>
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]Ø
 * Output: [2,2]
 * <p>
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 */
public class N350_IntersectionOfTwoArraysII {
    static int[] intersect(int[] nums1, int[] nums2) {
        // use hashmap.
        // foreach first array,element as key,num of appearances as value.
        // foreach another array,if hashmap contains the element,the value minus one.
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums1).forEach(t -> map.put(t, map.getOrDefault(t, 0) + 1));
        IntStream.Builder builder = IntStream.builder();
        Arrays.stream(nums2).forEach(t -> {
            if (map.containsKey(t) && map.get(t) > 0) {
                builder.add(t);
                map.put(t, map.get(t) - 1);
            }
        });
        return builder.build().toArray();

        // sort+pointer
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 3, 4, 5}, new int[]{1, 7, 9, 0, 2, 5})));
    }


}
