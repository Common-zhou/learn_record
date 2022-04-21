package com.zhou;

import java.util.regex.Pattern;

/**
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/04/21 19:31
 */
public interface Question {
  Pattern getPattern();

  void checkResult();
}
