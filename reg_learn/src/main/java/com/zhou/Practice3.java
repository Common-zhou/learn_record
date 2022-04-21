package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.regex.Pattern;

/**
 * 匹配邮箱号  3-10位数字 + @ qq.com
 *
 * @author zhoubing
 * @since 2022/04/21 19:30
 */
public class Practice3 implements Question{

  // 这个正则的含义是 ：以 字母 或者 _作为开头  (1，)
  // @ 连接
  // 连接一个 .
  // 字符串
  Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public void checkResult() {
    assertThat(ResultConstants.RESULT, getPattern().matcher("10500@qq.com").matches());
    assertThat(ResultConstants.RESULT, getPattern().matcher("10@qq.com").matches());
    assertThat(ResultConstants.RESULT, getPattern().matcher("105@qq.com").matches());
    assertThat(ResultConstants.RESULT, !getPattern().matcher("105qq.com").matches());

    assertThat(ResultConstants.RESULT, !getPattern().matcher("105qq@.com").matches());
    assertThat(ResultConstants.RESULT, getPattern().matcher("105qq@q.com").matches());
  }
}
