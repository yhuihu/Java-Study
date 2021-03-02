package com.study.leetcode.offer;

import java.util.BitSet;

/**
 * @author yhhu
 * @date 2021/3/1
 * @description
 */
public class FirstChar {
    public int FirstNotRepeatingChar(String str) {
        BitSet bitSet = new BitSet(128);
        BitSet bitSet1 = new BitSet(128);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!bitSet.get(c) && !bitSet1.get(c)) {
                bitSet.set(c);
            } else if (bitSet.get(c) && !bitSet1.get(c)) {
                bitSet1.set(c);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bitSet.get(c) && !bitSet1.get(c)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstChar firstChar = new FirstChar();
        String str = "helloh";
        int i = firstChar.FirstNotRepeatingChar(str);
        System.out.println(str.charAt(i));
    }
}
