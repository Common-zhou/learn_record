package com.zhou.doc;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavadocParameterNames extends BaseParameterNames {
	private static ExtClassDoc extClassDoc;
	public JavadocParameterNames(Class<?> clazz) {
		super(clazz);
		
	}

	@Override
	protected String[] doGetParameterNames(Member member) {
		if(extClassDoc != null && clazz != null){
			ClassDoc classDoc = extClassDoc.getClassDoc();
			if(classDoc != null){
				if (member instanceof Method){
					MethodDoc methodDoc = extClassDoc.getMethodDoc((Method) member);
					return Lists.transform(Arrays.asList(methodDoc.parameters()),new Function<Parameter,String>(){

						@Override
						public String apply(Parameter input) {
							return input.name();
						}}).toArray(new String[0]);
				} else if (member instanceof Constructor){
					
				} else {
					throw new IllegalArgumentException("member type must be Method or Constructor");
				}				
			}
			
		}
		return null;
	}

	public static ExtClassDoc getExtClassDoc() {
		return extClassDoc;
	}

	public static void setExtClassDoc(ExtClassDoc extClassDoc) {
		JavadocParameterNames.extClassDoc = extClassDoc;
	}

}