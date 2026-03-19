package com.双指针.接雨水;

/*
题目：
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
* */
public class Solution {
    public static void main(String[] args) {
        System.out.println(trap3(new int[]{2, 0, 2}));
    }

    /*
    方法一：双指针-定一移二
    思路：我们可以按“层”找能“夹住”水的格子，只要固定了一层，每层的循环内，问题可以简化成，找相邻两个格子之间有多少空格，空格之和即为答案,但是这样会超时
    * */
    public static int trap1(int[] height) {
        //0.结果集
        int result = 0;
        //1.定义数据
        int c = 1; //层数
        //寻找最大层数
        int maxC = 0;
        for (int i : height) {
            if (i > maxC) maxC = i;
        }
        while (c <= maxC) {
            int l = -1, r = -1;//左右指针
            //在一层中
            while (l < height.length - 2) {
                l++;
                //2.l指针：找该层中，相邻两个格子之间有多少空格
                //找第一个格子的位置,即当前指向对应的数字大于等于层数且下一位指向小于层数
                if (height[l] >= c && height[l + 1] < c) {
                    //3.r指针：从左指针出发，找下一个指向对应数字且大于等于层数
                    for (int i = l + 1; i < height.length; i++) {
                        if (height[i] >= c) {
                            r = i;
                            result += r - l - 1;
                            break;
                        }
                    }

                }
            }
            c++;
        }
        return result;
    }

    /*
    方法二：动态规划
    思路：下标为i的地方，能装下水的最高高度为，两边的高度的较小值-height[i] ， 同时向两边扫描，获取左右的最大高度，即可获取答案，但是这样做的时间复杂度是O(n^2)
        怎么优化呢？用时比较多的地方是，每次我们都要左右扫描整个数组，获取左右的最大高度的较小值，但其实我们可以通过预处理，定义一个leftMax和rightMax，
        以O（n）的时间复杂度，记录对应下标的左右最大值，然后可以直接查表，这样优化的时间复杂度是O（3n）
    * */
    public static int trap2(int[] height) {
        //0.结果集
        int result = 0;
        //1.预处理leftMax 和 rightMax
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int lMax = 0, rMax = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > lMax) lMax = height[i];
            leftMax[i] = lMax;
        }
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > rMax) rMax = height[i];
            rightMax[i] = rMax;
        }
        //2.遍历计算答案
        for (int i = 1; i < height.length - 1; i++) {
            int v = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (v > 0) result += v;
        }
        return result;
    }

    /*
    方法三：双指针：优化动态规划
    思路：在上面的动态规划中，我们进行了三次表的扫描，时间复杂度是O(n^3),继续简化，可以使用两个指针代替两个预定义数组，减少两次数组扫描，将时间复杂度降为真O(n)
* */
    public static int trap3(int[] height) {
        //0.结果集
        int result = 0;
        //1.定义两个指针和两个变量
        int l = 0, r = height.length - 1; //指针
        int leftMax = 0, rightMax = 0; //最大值变量记录
        //2.夹逼获取答案
        while (l<r){
            //维护leftMax和rightMax
            leftMax = Math.max(height[l],leftMax);
            rightMax = Math.max(height[r],rightMax);
            //当height[l]<height[r]时，必有 leftMax < rightMax , 此时，l处的存水量和右边无关了，只和左边的最大值有关,只需要用height[l] - leftMax即可算出此处存水量
            if (height[l]<height[r]){
                result += leftMax - height[l];
                l++;
            }else {
                result += rightMax - height[r];
                r--;
            }
        }
        return result;
    }
}
