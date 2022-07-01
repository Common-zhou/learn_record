package com.zhou;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zhoubing
 * @since 2022/07/01 23:39
 */
public class JunitSampleTest {
  @Test
  void testUsingJunitAssertThat() {
    // assert String
    String s = "abcde";

    Assertions.assertTrue(s.startsWith("ab"));
    Assertions.assertTrue(s.endsWith("de"));
    Assertions.assertEquals(5, s.length());

    // 数字判断
    Integer i = 50;
    Assertions.assertTrue(i > 0);
    Assertions.assertTrue(i < 100);


    // test date
    Date date1 = new Date();

    Date date2 = new Date(date1.getTime() + 100);
    Date date3 = new Date(date1.getTime() - 100);

    Assertions.assertTrue(date1.before(date2));
    Assertions.assertTrue(date1.after(date3));

    // test list
    List<String> strings = Arrays.asList("a", "b", "c", "d");
    Assertions.assertEquals(strings.get(0), "a");
    Assertions.assertEquals(4, strings.size());
    Assertions.assertEquals("d", strings.get(strings.size() - 1));

  }



}
