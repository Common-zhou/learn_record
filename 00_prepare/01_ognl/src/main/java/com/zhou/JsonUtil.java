package com.zhou;

import com.google.gson.Gson;

/**
 * @author zhoubing
 * @since 2022/08/12 17:07
 */
public class JsonUtil {
  private static final Gson GSON = new Gson();

  public <T> T fromJson(String str, Class<T> clz) {
    T obj = GSON.fromJson(str, clz);

    System.out.println("class: " + obj);
    return obj;
  }

}
