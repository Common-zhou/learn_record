package com.zhou.load;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhoubing
 * @date 2021-08-29 11:09
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
        IllegalAccessException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        // 虽然两者都属于 com.zhou.load.ClassLoaderTest
        // 但是 一个是由系统的类加载器加载的，一个是由自定义的类加载器加载的  所以类型检查还是false
        Object obj = myLoader.loadClass("com.zhou.load.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.zhou.load.ClassLoaderTest);
    }
}
