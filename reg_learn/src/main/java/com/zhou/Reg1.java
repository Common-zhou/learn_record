package com.zhou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhoubing
 * @since 2022/04/21 14:07
 */
public class Reg1 {
  public static void main(String[] args) {
    Reg1 reg1 = new Reg1();

    reg1.learnDot();
  }

  public void learnDot() {
    Pattern dotPattern = Pattern.compile(".ar");

    Matcher matcher = dotPattern.matcher("jar par hello world lar");

    while (matcher.find()) {

      for (int i = 0; i <= matcher.groupCount(); i++) {
        System.out.println(String.format("Group %d : %s", i, matcher.group(i)));
      }

    }

    boolean b = matcher.find(0);
    System.out.println(matcher.group(0));


  }

  public void practice(){
    Pattern dotPattern = Pattern.compile("([tT]his)");
    String str = "There This hope.zhou";
  }
}
