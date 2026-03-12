package com.双指针.移动零;

import java.util.Arrays;

/*
题目：
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
请注意 ，必须在不复制数组的情况下原地对数组进行操作。
示例 1:
输入: nums = [0,1,0,3,12]
输出: [1,3,12,0,0]
示例 2:
输入: nums = [0]
输出: [0]
* */
public class Solution {
    public static void main(String[] args) {
        moveZeroes2(new int[]{0, 1, 0, 3, 12});
    }

    /*
    方法一：首尾双指针(错误思路)
    思路：双指针，一个指向数组开头，一个指向数组结尾，首先由头指针推进，遇到0了就进入尾指针，尾指针推进寻找一个非0的数，两个指针的值交换（这样会破坏元素间的相对顺序），
    然后头指针继续推进，在任意推进过程两个指针如果相遇，则结束.
    * */
    public static void moveZeroes1(int[] nums) {
        //1.定义两个指针
        int index_head = 0, index_tail = nums.length - 1;
        //2.双指针
        while (index_head < index_tail) {
            if (nums[index_head] == 0) {
                //尾指针向头部移动
                while (index_head < index_tail) {
                    if (nums[index_tail] != 0) {
                        //交换
                        int temp = nums[index_tail];
                        nums[index_tail] = nums[index_head];
                        nums[index_head] = temp;
                        break;
                    }
                    index_tail--;
                }
            }
            index_head++;
        }
        System.out.println(Arrays.toString(nums));
    }

    /*
    方法二：0与非0双指针
    思路：双指针，一个指向0元素，一个找非0元素，非0指针找非0元素，找到了则看一下前面（从非零到零指针）是否有0，如果有则交换0和非零值，然后更新0指针的值，
    当非0指针遍历完整个数组，执行完毕。
    * */
    public static void moveZeroes2(int[] nums) {
        //1.定义两个指针
        int index_zero = -1, index_not_zero = 0;
        //2.确定零指针的初始指向
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                index_zero = i;
                break;
            }
        }
        if (index_zero==-1)return;
        for (; index_not_zero < nums.length; index_not_zero++) {
            if (nums[index_not_zero]!=0 && index_not_zero > index_zero){
                nums[index_zero] = nums[index_not_zero];
                nums[index_not_zero] = 0;
                //找下一个0的位置
                for (; index_zero < nums.length ; index_zero++) {
                    if (nums[index_zero]==0){
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(nums));

    }

}
