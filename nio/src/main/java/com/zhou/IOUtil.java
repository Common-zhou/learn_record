package com.zhou;

import io.netty.util.internal.StringUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 从流中读出数据
 *
 * @author zhoubing
 * @date 2022-03-27 10:17
 */
public class IOUtil {
    public static String readInputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return StringUtil.EMPTY_STRING;
        }
        byte[] bytes = new byte[2 * 1024];
        int len;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();

        return bos.toString();
    }
}
