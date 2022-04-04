package com.zhou.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoubing
 * @date 2022-04-04 17:00
 */
public class ArraysTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhou", "bing", "hello world");
        //list.add("hello");

        Collection<Object> objects = Collections.unmodifiableCollection(new ArrayList<>());
        //objects.add("hello");
    }
}
