package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * **匹配连续出现的四组以.连接的字符串**
 * <p>
 * 题目：abc.dss.sds.wer
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/22 17:11
 */
public class Practice5 implements Question {
  Pattern pattern = Pattern.compile("^([a-zA-Z]+[.]){3}[a-zA-Z]+$");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public void checkResult() {
    List<String> passedCase = Arrays.asList("abc.dss.sds.wer", "a.b.v.d", "aa.bg.vf.dd");
    List<String> notPassedCase = Arrays.asList("abc.dss.sds", "abc.dss.sds.wer1", "aa.bg.vf.d6d");

    for (String oneCase : passedCase) {
      assertThat(ResultConstants.RESULT, getPattern().matcher(oneCase).matches());
    }

    for (String oneCase : notPassedCase) {
      assertThat(ResultConstants.RESULT, !getPattern().matcher(oneCase).matches());
    }

  }

}
