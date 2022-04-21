package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.regex.Pattern;

/**
 * 匹配电话号码
 * 11位 1开头
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/21 20:07
 */
public class Practice4 implements Question {

  Pattern pattern = Pattern.compile("^1[3456789][\\d]{9}$");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public void checkResult() {
    assertThat(ResultConstants.RESULT, !getPattern().matcher("110").matches());
    assertThat(ResultConstants.RESULT, getPattern().matcher("17683788078").matches());
    assertThat(ResultConstants.RESULT, !getPattern().matcher("11683788078").matches());
    assertThat(ResultConstants.RESULT, getPattern().matcher("13683788078").matches());
  }

}
