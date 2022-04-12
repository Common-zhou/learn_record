package com.zhou;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-13 00:18
 */
public class GuavaDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "g", "9", "8");

        String join = Joiner.on(',').join(list);
        System.out.println(join);

        String needSplit = "hello,world,,,,hhh,nihao";
        List<String> splitList = Splitter.on(",").splitToList(needSplit);

        System.out.println(splitList);


        List<Integer> elments = Lists.newArrayList(4, 2, 3, 5, 1, 2, 2, 7, 6);
        List<List<Integer>> partition = Lists.partition(elments, 4);
        partition.forEach(System.out::println);

        // 一个key  可以存多个value
        Multimap<Integer, Integer> bMultimap = ArrayListMultimap.create();

        elments.forEach(
            a -> bMultimap.put(a, a + 1)
        );

        System.out.println(bMultimap);

        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(10, "zhangsan");
        biMap.put(20, "lisi");
        biMap.put(35, "wangwu");

        System.out.println(biMap.get(10));
        System.out.println(biMap.get("lisi"));
        System.out.println(biMap.inverse().get("lisi"));


    }
}
