package com.zhou;

import static com.zhou.constants.ResultConstants.RESULT;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.regex.Pattern;

/**
 * 1.写一个正则表达式，使其能同时识别下面所有的字符串：’bat’,’bit’, ‘but’, ‘hat’, ‘hit’, ‘hut’
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/21 18:50
 */
public class Practice1 implements Question {
  Pattern pattern = Pattern.compile("(bat|bit|but|hat|hit|hut)");
  Pattern pattern1 = Pattern.compile("^(b|h)(a|i|u)t$");

  @Override
  public Pattern getPattern() {
    return pattern1;
  }

  @Override
  public void checkResult() {

    assertThat(RESULT, getPattern().matcher("bat").matches());
    assertThat(RESULT, getPattern().matcher("bit").matches());
    assertThat(RESULT, getPattern().matcher("but").matches());
    assertThat(RESULT, getPattern().matcher("hat").matches());
    assertThat(RESULT, getPattern().matcher("hit").matches());
    assertThat(RESULT, getPattern().matcher("hut").matches());
    assertThat(RESULT, !getPattern().matcher("huu").matches());
  }


}
