package com.双指针.盛水最多的容器;

/*
题目：
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
说明：你不能倾斜容器。
* */
public class Solution {
    public static void main(String[] args) {
        System.out.println(maxArea3(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    /*
    方法一：暴力
    思路：双指针暴力枚举，固定一个指针，遍历另一个指针，寻找最大容积.但是这样的方式的时间复杂度很明显是O(n^2)
    * */
    public static int maxArea1(int[] height) {
        int maxV = 0 ;
        for (int i = 0; i < height.length-1; i++) {
            //i为指针1
            for (int j = i+1; j < height.length; j++) {
                int tall = Math.min(height[i],height[j] );
                maxV = Math.max(tall*(j-i),maxV);
            }
        }
        return maxV;
    }

    /*
    方法二：暴力剪枝优化
    思路：可见，如果按第一种暴力的方式去遍历，我们会做大量的无用功，我们可以加一个记录值，记录第一个指针的最大值，如果下一个值没有大于这个值，
        其实可以直接跳过这次遍历，意思就是“不可能获得比之前更优的结果”，进行剪枝。此优化已经可以通过力扣的时间限制。
    * */
    public static int maxArea2(int[] height) {
        int maxV = 0 , maxLeft=0;
        for (int i = 0; i < height.length-1; i++) {
            if(maxLeft<height[i]){
                //只有当有可能获取更好的结果的时候，才进行遍历
                maxLeft = height[i];
                for (int j = i+1; j < height.length; j++) {
                    int tall = Math.min(height[i],height[j] );
                    maxV = Math.max(tall*(j-i),maxV);
                }
            }

        }
        return maxV;
    }

    /*
   方法三：经典双指针
   思路：指针一左一右，根据：总容积=短边高度*两指针的距离 ，可以论证：每次都移动短边，才有可能获得比上次更好的结果，当双指针相遇，举例结束，
        这个方法只需要遍历一次数组，时间复杂度为O(n)
   * */
    public static int maxArea3(int[] height) {
        int maxV = 0 , index_left = 0 ,index_right= height.length-1;
        while (index_left<index_right){
            //记录容积
            maxV=Math.max( ((Math.min(height[index_left],height[index_right])) * (index_right-index_left)),maxV);
            if (height[index_left]<height[index_right]){
                //左指针较短，移动左指针
                index_left++;
            }else {
                index_right--;
            }
        }
        return maxV;
    }
}
