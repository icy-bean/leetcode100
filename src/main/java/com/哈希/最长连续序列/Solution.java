package com.哈希.最长连续序列;

/*
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
* */

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) {
        System.out.println(longestConsecutive2(new int[]{100, 4, 200, 1, 3, 2}));
    }

    /*
    方法一：暴力枚举
    思路：对于每一个数x，寻找x+1 , x+2 ,...,x+y是否存在，不断更新答案.这样的做的时间复杂度会爆炸，是O(n^2)，无法通过所有检查点
    * */
    public static int longestConsecutive1(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            //1.对于数字nums[i]
            int num = nums[i];
            //2.暴力枚举
            int cot = 1;
            boolean flag = true;
            while (flag) {
                for (int j = 0; j < nums.length; j++) {
                    //2.1寻找是否存在num+1
                    if (num + 1 == nums[j]) {
                        //存在
                        num++;
                        cot++;
                        break;
                    }
                    if (j == nums.length - 1) flag = false;
                }
            }
            //走到这里，cot记录的就是以一开始的num为起点，走得最长的距离
            if (result < cot) result = cot;
        }
        //便利完所有数字，返回答案
        return result;
    }

    /*
    方法二：哈希表（优化暴力枚举）
    思路：我们可以利用哈希表，把所有需要查询的数字存入哈希表中，这样每次查询都是O(1)的，不用遍历整个数组，但是这样做还是需要做大量不必要的循环，比如已经有x到x+7了，遇到x+3还是
           不断查询，我们可以跳过的。当拿到一个新数的时候，查询一下它的前驱数是否已经在表中了，如果在，则可以跳过。
    * */
    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        int reslut = 1;
        //1.先把数组中所有的数存入哈希表,这里注意去重
        HashSet<Integer> hash_nums = new HashSet<>();
        for (int num : nums) {
            hash_nums.add(num);
        }
        //2.遍历整个数组，寻找答案,注意，这里遍历的是去重后的set集合！而不是原数组，可以避免大量重复的元素
        for (int num : hash_nums) {
            //定义必要的数据结构

            //2.1查询num的前驱数是否存在
            if (!hash_nums.contains(num - 1)) {
                //2.2前驱数不存在
                int temp = 1;
                while (true) {
                    if (hash_nums.contains(num + temp)) {
                        temp++;
                    } else {
                        break;
                    }
                }
                reslut = Math.max(temp, reslut);
            }

        }
        return reslut;
    }
}
