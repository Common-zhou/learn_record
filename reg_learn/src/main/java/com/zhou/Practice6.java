package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 判断字符串是否包含数字
 * @author zhoubing
 * @since 2022/04/22 17:50
 */
public class Practice6 implements Question{
  Pattern pattern = Pattern.compile("(\\d)");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public void checkResult() {
    List<String> passedCase = Arrays.asList("123asd45465");
    List<String> notPassedCase = Arrays.asList("abc.dss.sds", "abc.dss.sds.wer", "aa.bg.vfd");

    for (String oneCase : passedCase) {
      assertThat(ResultConstants.RESULT, getPattern().matcher(oneCase).find());
    }

    for (String oneCase : notPassedCase) {
      assertThat(ResultConstants.RESULT, !getPattern().matcher(oneCase).find());
    }

  }

}
