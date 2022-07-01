package com.zhou;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zhoubing
 * @since 2022/07/01 23:56
 */
public class AspectjSampleTest {
  @Test
  void testWithAspectJ() {
    String s = "abcde";

    Assertions.assertThat(s).as("字符串判断：判断首尾及长度")
        .startsWith("ab").endsWith("de").hasSize(5);

    // 数字判断
    Integer i = 50;

    Assertions.assertThat(i).as("数字判断：数字大小比较")
        .isGreaterThan(0).isLessThan(100);

    // test date
    Date date1 = new Date();

    Date date2 = new Date(date1.getTime() + 100);
    Date date3 = new Date(date1.getTime() - 100);

    Assertions.assertThat(date1).as("数字判断：数字大小比较")
        .isBefore(date2).isAfter(date3);

    // test list
    List<String> strings = Arrays.asList("a", "b", "c", "d");

    Assertions.assertThat(strings).as("数字判断：数字大小比较")
        .startsWith("a").endsWith("d").hasSize(4);

  }
}
