package com.zhou.bytecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 这个方法是为了自定义导出class文件
 * 将所有的byte 变成 255-x
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/03/06 22:43
 */
public class DecodeClassCustom {
  public static void main(String[] args) throws IOException {
    File file = new File("/Users/zhoubing/IdeaProjects/learn_record/jvm/src/main/java/com/zhou/bytecode/Hello.class");

    Long length = file.length();

    byte[] bytes = new byte[length.intValue()];

    FileInputStream fileInputStream = new FileInputStream(file);
    int read = fileInputStream.read(bytes);
    fileInputStream.close();

    for (int i = 0; i < length; i++) {
      bytes[i] = (byte) (255 - bytes[i]);
    }

    FileOutputStream fileOutputStream = new FileOutputStream(new File(file.getParent() + "/HelloCus.class"));
    fileOutputStream.write(bytes);
    fileOutputStream.close();

  }
}
