package com.zhou;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author zhoubing
 * @date 2022-04-13 00:02
 */
public class CollectionDemo {
    public static void main(String[] args) {
        //测试guava的排序
        List<Integer> list = Arrays.asList(1, 6, 8, 5, 3, 6, 8, 5);

        // 排序
        Collections.sort(list);

        print(list);

        // 打散 随机
        Collections.shuffle(list);
        print(list);

        // 众数
        System.out.println(Collections.frequency(list, 8));

        // 最大数
        System.out.println(Collections.max(list));

        // 单数组 不可变 不太懂使用场景
        System.out.println(Collections.singletonList(6));

    }

    private static void print(List<Integer> list) {

        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        list.stream().peek(x -> stringJoiner.add(x.toString())).count();

        System.out.println(stringJoiner);
    }
}
