package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 给定字符串 str，检查其是否符合美元书写格式
 * <p>
 * - 以 $ 开始
 * - 整数部分，从个位起，满 3 个数字用 , 分隔
 * - 如果为小数，则小数部分长度为 2
 * - 正确的格式如：$1,023,032.03 或者 $2.03，错误的格式如：$3,432,12.12 或者 $34,344.3**
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/22 17:57
 */
public class Practice7 implements Question {

  Pattern pattern = Pattern.compile("\\$\\d{1,3}(,\\d{3})*(.\\d{2})?$");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  public static void main(String[] args) {
    new Practice7().checkResult();
  }

  @Override
  public void checkResult() {
    List<String> passedCase = Arrays.asList("$1,023,032.03","$1,023,032.03","$123,023,032.03");
    List<String> notPassedCase = Arrays.asList("$3,432,12.12","$1234,023,032.03","$0034,023,032.03");

    for (String oneCase : passedCase) {
      assertThat(ResultConstants.RESULT, getPattern().matcher(oneCase).find());
    }

    for (String oneCase : notPassedCase) {
      assertThat(ResultConstants.RESULT, !getPattern().matcher(oneCase).find());
    }
  }
}
