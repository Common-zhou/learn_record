package com.zhou;

import com.zhou.prepare.User;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/29 16:22
 */
public class SortByField {
  public static void main(String[] args) {
    User u1 = new User(1, "zhangsan");
    User u2 = new User(2, "lisi");
    User u3 = new User(3, "wangwu");
    User u4 = new User(4, "hello");

    List<User> users = Arrays.asList(u1, u2, u3, u4);

    String byField = "age";
    //
    //users.sort((c1, c2) -> {
    //
    //});

    System.out.println(users);

  }
}
