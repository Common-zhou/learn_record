package com.zhou;

import ognl.DefaultClassResolver;
import ognl.DefaultTypeConverter;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @author zhoubing
 * @since 2022/08/12 16:52
 */
public class TestOgnl {
  public static void main(String[] args) throws OgnlException {

    ADemo a = new ADemo();
    a.setName("zhouzhou");
    a.setAge(18);

    PrintDemo printDemo = new PrintDemo();
    printDemo.setPrefix("ognl");
    printDemo.setADemo(a);

    TestOgnl testOgnl = new TestOgnl();
    OgnlContext context = (OgnlContext) Ognl.createDefaultContext(testOgnl, new DefaultMemberAccess(true),
        new DefaultClassResolver(), new DefaultTypeConverter());
    context.setRoot(printDemo);

    context.put("print", printDemo);
    context.put("a", a);


    Object ans = Ognl.getValue(Ognl.parseExpression("#print.sayHello(\"zhangsan\", 166)"), context, context.getRoot());

    System.out.println(ans);

  }
}
