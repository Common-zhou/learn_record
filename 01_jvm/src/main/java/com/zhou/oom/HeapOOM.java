package com.zhou.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 * @author zhoubing
 * @date 2021-08-29 17:18
 */
public class HeapOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
