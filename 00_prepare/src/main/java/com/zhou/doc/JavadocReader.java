package com.zhou.doc;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import java.io.File;
import java.util.List;

/**
 * 读取指定java源文件的javadoc信息
 * @author guyadong
 *
 */
public class JavadocReader {
	private static RootDoc root = null;
	private static String sourcepath;
	private static String[] sourcepathList = new String[0];
	private static String classpath;

	public static  class Doclet {
	    public static boolean start(RootDoc root) {
	    	JavadocReader.root = root;
	        return true;
	    }
	}
	public static void show(){
		if(null == root){
			return ;
		}
        ClassDoc[] classes = root.classes();
        for (int i = 0; i < classes.length; ++i) {
        	new ExtClassDoc(classes[i]).output(System.out);
        }
	}
	/**
	 * 解析指定的java源文件返回javadoc对象 {@link RootDoc}<br>
	 * 参见 <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/javadoc/standard-doclet.html#runningprogrammatically">Running the Standard Doclet Programmatically</a>
	 * @param source a java source file or package name
	 * @param classpath value for  '-classpath',{@code source}的class位置,可为{@code null},如果不提供,无法获取到完整的注释信息(比如annotation)
	 * @param sourcepath value for '-sourcepath'
	 * @return {@link RootDoc}对象
	 */
	public synchronized static RootDoc readDocs(String source, String classpath,String sourcepath) {
		checkArgument(!Strings.isNullOrEmpty(source), "source is null");
		List<String> args = Lists.newArrayList("-doclet", 
				Doclet.class.getName(), "-quiet","-Xmaxerrs","1","-Xmaxwarns","1","-encoding","utf-8","-private");
		if(!Strings.isNullOrEmpty(classpath)){
			args.add("-classpath");
			args.add(classpath);
		}
		if(!Strings.isNullOrEmpty(sourcepath)){
			args.add("-sourcepath");
			args.add(sourcepath);
		}
		args.add(source);
		int returnCode = com.sun.tools.javadoc.Main.execute(JavadocReader.class.getClassLoader(),args.toArray(new String[args.size()]));

		if(0 != returnCode){
			System.out.printf("javadoc ERROR CODE = %d\n", returnCode);
			throw new IllegalStateException();
		}
		return root;
	}
	/**
	 * 读取{@code source}指定源文件的注释
	 * @param source
	 * @param classpath
	 * @param sourcepath
	 * @return 读取失败返回{@code null}
	 */
	public synchronized static ExtClassDoc read(String source, String classpath,String sourcepath) {
		try {
			return new ExtClassDoc(readDocs(source, classpath, sourcepath).classes()[0]);	
		} catch (IllegalStateException e) {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	/** @see #read(String, String) */
	public static ExtClassDoc read(String source) {
		return read(source,classpath);
	}
	public static ExtClassDoc read(String prefix,Class<?> clazz) {
		return read(getSourceFile(prefix,clazz),classpath);
	}
	
	/** 
	 * {@link ExtClassDoc}缓存对象,
	 * 保存每个类对应的{@link ExtClassDoc}实例 
	 */
	private static final LoadingCache<Class<?>,ExtClassDoc> 
		docCache = 
			CacheBuilder.newBuilder().build(
					new CacheLoader<Class<?>,ExtClassDoc>(){
						@Override
						public ExtClassDoc load(Class<?> key) throws Exception {
							return checkNotNull(read0(key),"NOT FOUND javadoc for %s",key.getName());
						}});

	private static ExtClassDoc read0(Class<?> clazz) {
		try{
			RootDoc rootDoc = readDocs(getSourceFile(clazz), classpath, sourcepath);
			if(rootDoc == null){
				return null;
			}	
			String fullname = clazz.getName().replace('$', '.');
			for(ClassDoc doc:rootDoc.classes()){			
				if(doc.qualifiedName().equals(fullname)){
					return new ExtClassDoc(doc);
				}
			}
			throw new IllegalStateException("NOT FOUND JAVADOC FOR " + clazz.getName());
		} catch (IllegalStateException e) {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	public static ExtClassDoc read(Class<?> clazz) {
		try{
			return docCache.get(clazz);
		} catch (Exception e) {
			return null;
		}
	}
	public static ExtClassDoc read(String source, List<String> classpath) {
		return read(source,null == classpath?null:Joiner.on(File.pathSeparator).skipNulls().join(classpath));
	}
	public static ExtClassDoc read(String source, String[] classpath) {
		return read(source,null == classpath?null:Joiner.on(File.pathSeparator).skipNulls().join(classpath));
	}
	public synchronized static ExtClassDoc read(String source, String classpath) {
		return read(source,classpath,sourcepath);
	}
	public static ExtClassDoc read(String source, List<String> classpath,List<String> sourcepath) {
		return read(source,
				null == classpath ? null : Joiner.on(File.pathSeparator).skipNulls().join(classpath),
				null == sourcepath ? null : Joiner.on(",").skipNulls().join(sourcepath));
	}
	public static ExtClassDoc read(String source, String[] classpath,String[] sourcepath) {
		return read(source,
				null == classpath ? null : Joiner.on(File.pathSeparator).skipNulls().join(classpath),
				null == sourcepath ? null : Joiner.on(",").skipNulls().join(sourcepath));
	}
	/**
	 * 返回类的源文件位置
	 * @param prefix 源文件夹,可为{@code null}
	 * @param clazz 为{@code null}则返回{@code null}
	 * @return 返回'/'分隔'.java'结尾的路径名,for example, '/home/src/java/awt/Graphics*java‘
	 */
	public static String getSourceFile(String prefix,Class<?> clazz){
		if(null != clazz ){
			Class<?> innerClass = clazz;
			Class<?> declaringClass = innerClass.getDeclaringClass();
			while(declaringClass != null){
				innerClass = declaringClass;
				declaringClass = declaringClass.getDeclaringClass();
			}
			String source = innerClass.getName().replace('.', File.separatorChar) + ".java";
			return prefix == null ? source : prefix + File.separator + source;
		}
		return null;
	}
	/**
	 * @param clazz
	 * @return file path
	 * @see #getSourceFile(String, Class)
	 */
	public static String getSourceFile(Class<?> clazz){
		for(String path:sourcepathList){
			String source = getSourceFile(path, clazz);
			if(new File(source).isFile()){
				return source;
			}
		}
		return getSourceFile(null, clazz);
	}
	/**
	 * @return sourcepath
	 */
	public static String getSourcepath() {
		return sourcepath;
	}
	/**
	 * @param sourcepath 要设置的 sourcepath
	 */
	public static void setSourcepath(String sourcepath) {
		JavadocReader.sourcepath = sourcepath;
		if(!Strings.isNullOrEmpty(sourcepath)){
			sourcepathList = sourcepath.split("\\s*[,;]\\s*");
		}
	}
	/**
	 * @return classpath
	 */
	public static String getClasspath() {
		return classpath;
	}
	/**
	 * @param classpath 要设置的 classpath
	 */
	public static void setClasspath(String classpath) {
		JavadocReader.classpath = classpath;
	}
}