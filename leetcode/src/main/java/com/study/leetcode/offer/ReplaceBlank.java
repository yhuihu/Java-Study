package com.study.leetcode.offer;

/**
 * @author yhhu
 * @date 2021/2/27
 * @description
 */
public class ReplaceBlank {
    public String replaceSpace(StringBuffer str) {
        int length = str.length() - 1;
        for (int i = 0; i <= length; i++) {
            if (str.charAt(i) == ' ') {
                // 新增两个空格->%20
                str.append("  ");
            }
        }
        // 增加完字符后的长度
        int trueLength = str.length() - 1;
        while (length >= 0 && trueLength > length) {
            char nowChar = str.charAt(length--);
            // 因为是从后往前找，因此在找到空格时，它的后两位也是空格
            if (nowChar == ' ') {
                str.setCharAt(trueLength--, '0');
                str.setCharAt(trueLength--, '2');
                str.setCharAt(trueLength--, '%');
            } else {
                // 如果当前位置不是空格，则应当移动到真实长度的靠后位置
                str.setCharAt(trueLength--, nowChar);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        ReplaceBlank replaceBlank = new ReplaceBlank();
        StringBuffer str = new StringBuffer("A B");
        System.out.println(replaceBlank.replaceSpace(str));
    }
}
