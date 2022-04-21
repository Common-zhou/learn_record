package com.zhou;

import static org.hamcrest.MatcherAssert.assertThat;

import com.zhou.constants.ResultConstants;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 2.匹配由单个空格分隔的任意单词对，也就是姓和名
 *
 * @author zhoubing
 * @since 2022/04/21 19:13
 */
public class Practice2 implements Question{

  Pattern pattern = Pattern.compile("((^[a-zA-Z]+) ([a-zA-Z]+)$)");

  @Override
  public Pattern getPattern() {
    return pattern;
  }

  @Override
  public void checkResult() {
    String[] keys = {"li lei", "han meimei", "jack u", "wu yifan", "sdfsdf"};
    Map<String, Boolean> resultMap = Arrays.stream(keys).collect(Collectors.toMap(Function.identity(), (a) -> true));
    resultMap.put("sdfsdf", false);

    for (String key : keys) {
      boolean mr = getPattern().matcher(key).matches();
      Boolean rResult = resultMap.get(key);
      assertThat(ResultConstants.RESULT + ":  "+  key, mr == rResult);
    }
  }

}
