package com.study.netty;

/**
 * @author yhhu
 * @date 2021/10/25
 * @description 测试netty当中的位运算符操作  当NioEventLoop为偶数时，使用与运算，为奇数时取模
 */
public class ChooseTest {
    public static void main(String[] args) {
        Integer tmpIsDouble = 6;
        Integer tmpNotDouble = 5;

        for (int i = 0; i < 6; i++) {
            System.out.println("通过偶数进行与运算结果：" + (i & tmpIsDouble - 1));
            // 这里是错的
            System.out.println("通过奇数进行与运算结果：" + (i & tmpNotDouble - 1));
        }
    }
}
