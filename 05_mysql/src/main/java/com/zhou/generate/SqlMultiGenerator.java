package com.zhou.generate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 单条sql插入。使用shell生成太慢了。
 *
 * @author zhoubing
 * @since 2022/04/25 19:27
 */
public class SqlMultiGenerator {
  public static void main(String[] args) throws IOException {
    int length = 1_000_000;

    int batch_size = 10000;

    StringBuilder stringBuilder =
        new StringBuilder("");

    for (int i = 0; i < length; i++) {

      if (i % batch_size == 0) {
        // 到达了批次
        stringBuilder.append(";\n");
        stringBuilder.append("insert into user(name,password,salt,birthday,create_time,update_time) values ");
        stringBuilder.append(String.format("('name%d', 'password%d','salt',now(),now(),now())", i, i));
      } else {
        String sql = String.format(
            ", ('name%d', 'password%d','salt',now(),now(),now())", i, i);

        stringBuilder.append(sql);
      }
    }
    stringBuilder.append(";");

    BufferedWriter out = new BufferedWriter(new FileWriter("b.sql"));
    out.write(stringBuilder.toString());

    out.close();

  }

}
