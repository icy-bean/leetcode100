package com.哈希.两数之和;

import java.util.Arrays;
import java.util.HashMap;

/*
题目：
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
你可以按任意顺序返回答案。
* */
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9)));
    }

    /*
    方法一：暴力循环，对于数组中的每个数字x ，循环寻找另一个符合相加等于target的数字target-x，时间复杂度为O（n^2），空间复杂度为O(1)
    * */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j} ;
                }
            }
        }
        return null;
    }

    /*
    方法二：哈希优化
    思路：对于第一种方法，耗时最大的步骤就是找数字target-x的步骤，此时可以使用哈希表的特点，找一个键的时间是O(1)的。
    实现：键存每个检验过的值，值存索引。对于每个数组中的值，判断哈希表中是否存在target-x （时间复杂度是O（1）），如果不存在，把自己存进去，如果存在，即为答案，返回。
    * */
    public static int[] twoSum2(int[] nums, int target) {
        //1.定义哈希数据结构
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        //2.循环列表中的每一个值
        for (int i = 0 ; i < nums.length ; i++ ) {
            //2.1查找哈希表中是否已经存在符合要求的键
            if (hashMap.containsKey(target-nums[i])){
                //存在,返回结果
                return new int[]{i,hashMap.get(target-nums[i])} ;
            }else {
                //不存在,把自己存进哈希表中
                hashMap.put(nums[i],i);
            }
        }
        return null;
    }
}
