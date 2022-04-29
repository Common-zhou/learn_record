package com.zhou.primary_key;

/**
 * 获取gap数据的实现方式
 *
 * @author zhoubing
 * @date 2022-04-29 23:37
 */
public interface FetchGapData {
    /**
     * 获取一个gap的数据。不关心数据是啥。但是得唯一
     * @param gap 需要获取的数据个数
     * @return
     */
    Ranger fetchGap(int gap);
}
