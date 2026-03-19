package com.双指针.三数之和;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/*
题目：
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
* */
public class Solution {
    public static void main(String[] args) {
        if (threeSum(new int[]{-1, 0, 1, 2, -1, -4}) != null) {
            System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        }
    }

    /*
    方法一：双指针-定一移二
    思路：先将数组进行排序，时间复杂度是O(nlogn)固定一个指针，移动左右指针，左指针指向固定指针的右边一位，右指针指向最大值。当三指针对应的值相加大于0，右指针左移，反之左指针右移，
        当有指针相遇（左右指针相遇或者右指针遇到固定指针）还没找到答案时，结束一次循环，固定指针右移动，这一步的时间复杂度是O(n),所以整体的时间复杂度是O(nlogn)
    * */
    public static List<List<Integer>> threeSum(int[] nums) {
        //0.定义结果集，使用Set去重
        HashSet<List<Integer>> result = new HashSet<>();
        //1.数组排序
        Arrays.sort(nums);
        //2.固定指针循环
        for (int i = 0; i < nums.length - 2; i++) {
            //此处有优化：只有当和上次的固定指针值不一样时，我们才做枚举
            if (i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            //3.定义左右指针
            int l = i + 1, r = nums.length-1;
            while (l<r&&i<r){
                //4.计算三者指和
                long sum = nums[i] + nums[l] + nums[r];
                if (sum==0){
                    //找到一组答案
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    //继续推进
                    l++;
                }else if (sum>0){
                    r--;
                }else {
                    l++;
                }
            }
        }
        return new ArrayList<>(result);
    }
}
