package com.zhou.doc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zhoubing
 * @version 1.0.0
 * @since 2022/06/08 19:46
 */
public class TestJavaDoc {
  public static void main(String[] args) {
    //testReadDoc();
    //testReadDocs();


  }

  private static void testReadDoc() {
    ExtClassDoc doc = JavadocReader.read(
        "/Users/zhoubing/IdeaProjects/learn_record/00_prepare/src/main/java/com/zhou/doc/MUserBean.java");
//		Method method = FaceLogDefinition.class.getMethod("setPersonExpiryDate", int.class,long.class);
//		String methodDoc = doc.getMethodComment(method);
    doc.addExcludeTag("@throws");
    //ExtClassDoc.additionalText("hello,add begin of class", Action.ADD, Type.CLASS);
    doc.additionalText("hello,add END of comment", "OVERWRITE", "CLASS");
//		for(Method method:FaceLogDefinition.class.getDeclaredMethods()){
//			System.out.println(method);
//			MethodDocImpl methodDoc = doc.getMethodDoc(method);
//			if(null != methodDoc){
//				System.out.println("matched:" + methodDoc);
//			}else
//				System.out.println("not matched ");
//		}
    doc.output(System.out);
//		show();
  }

  public static void testReadDocs() {
    RootDoc rootDoc =
        JavadocReader.readDocs("com.zhou.doc", "/Users/zhoubing/IdeaProjects/learn_record/00_prepare/target/classes",
            "/Users/zhoubing/IdeaProjects/learn_record/00_prepare/src/main/java");
    //RootDoc rootDoc = JavadocReader.readDocs("net.gdface.service.facelog","J:/facelog/db2/target/classes","J:/facelog/facelog-service/src/main/java");

    Map<String, ClassDoc> map = new HashMap<>();
    for (ClassDoc classDoc : rootDoc.classes()) {

      System.out.println(classDoc.name());

      map.put(classDoc.name(), classDoc);
    }
  }


}
