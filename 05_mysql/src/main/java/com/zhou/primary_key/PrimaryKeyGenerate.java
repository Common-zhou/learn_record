package com.zhou.primary_key;

/**
 * 这个主要是用来生成唯一id的。使用题目所说的方法，
 * 使用数据库完成这个动作。
 * <pre>
 *     1. 给数据库加锁，拿回来一个数字。
 *     2. 拿回来这个数字后。可以往上+100（gap）。
 *     3. 最终将所有东西都写入一个数据库(批量写入。这个数据库的key是唯一键(用来检验是否会生成重复的))
 * </pre>
 * @author zhoubing
 * @date 2022-04-29 23:23
 */
public class PrimaryKeyGenerate {
    public static void main(String[] args) {

    }
}
