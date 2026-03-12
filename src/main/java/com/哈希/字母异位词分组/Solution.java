package com.哈希.字母异位词分组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
题目：
给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
解释：
在 strs 中没有字符串可以通过重新排列来形成 "bat"。
字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
* */

public class Solution {

    public static void main(String[] args) {
        System.out.println(groupAnagrams1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    /*
    方法一：对每个字符串进行排序，对于一组字母异位词，他们的排序结果肯定是一样的，那就可以用排序后的结果为键，值为结果列表
    * */
    public static List<List<String>> groupAnagrams1(String[] strs) {
        //1.定义哈希表
        HashMap<String, List<String>> hashMap = new HashMap<>();
        //2.遍历字符串数组
        for (String str : strs) {
            //2.1 排序
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String string = Arrays.toString(charArray);
            /*
            Java HashMap.getOrDefault() 方法解析:getOrDefault()是 HashMap类的一个便捷方法，用于获取指定键对应的值，如果键不存在，则返回指定的默认值
            * */
            List<String> list = hashMap.getOrDefault(string, new ArrayList<>());
            list.add(str);
            hashMap.put(string,list);

        }
        //3.全部值循环完毕，返回结果
        /*
        values()是 HashMap类的一个方法，用于返回此映射中包含的值的集合视图。这个集合由原始的HashMap支持，对HashMap的任何修改都会反映在返回的集合中，反之亦然。
        * */
        return new ArrayList<List<String>>(hashMap.values());
    }
}
