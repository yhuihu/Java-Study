package com.study.leetcode;

/**
 * @author Tiger
 * @date 2020-06-09
 * @see com.study.leetcode
 **/
public class Question14 {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * <p>
     * 所有输入只包含小写字母 a-z 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String solution(String[] str) {
        if (str.length == 0) {
            return "";
        }
        String ans = str[0];
        for (int i = 1; i < str.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < str[i].length(); j++) {
                if (ans.charAt(j) != str[i].charAt(j)) {
                    break;
                }
            }
            ans = ans.substring(0, j);
            if ("".equals(ans)) {
                break;
            }
        }
        return ans;
    }
}
