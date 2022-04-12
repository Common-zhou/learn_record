package com.zhou;

import static java.util.stream.Collectors.toMap;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhoubing
 * @date 2022-04-12 23:49
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 5, 6, 5, 8, 9, 6, 9, 7, 9, 7);
        //List<Integer> list = Arrays.asList(1, 2, 2);

        Optional<Integer> first = list.stream().findFirst();
        Integer integer = first.map(x -> x + 6).orElse(6);
        System.out.println(integer);

        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);


        //Map<Integer, Integer> collect = list.stream().collect(toMap(a -> a, a -> a + 6));
        Map<Integer, Integer> collect =
            list.stream().collect(toMap(a -> a, a -> a + 6, (a, b) -> a, LinkedHashMap::new));
        System.out.println(collect);

        //Map<Integer, Integer> collect1 = list.stream().collect(toMap(Function.identity(), Function.identity()));
        //System.out.println(collect1);

        collect.entrySet().parallelStream().forEach(System.out::println);

        collect.forEach((a, b) -> {
            System.out.println(a + "==>" + b);
        });

    }
}
