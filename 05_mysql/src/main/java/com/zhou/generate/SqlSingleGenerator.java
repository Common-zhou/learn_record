package com.zhou.generate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 单条sql插入。使用shell生成太慢了。
 * @author zhoubing
 * @since 2022/04/25 19:27
 */
public class SqlSingleGenerator {
  public static void main(String[] args) throws IOException {
    int length = 1_000_000;

    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < length; i++) {
      String sql = String.format(
          "insert into user(name,password,salt,birthday,create_time,update_time) values(concat('name',%d), concat('password', %d),'salt',now(),now(),now());\n",
          i, i);

      stringBuilder.append(sql);
    }
    BufferedWriter out = new BufferedWriter(new FileWriter("a.sql"));
    out.write(stringBuilder.toString());

    out.close();

  }

}
