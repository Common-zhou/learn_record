package com.zhou.service;

/**
 * @author zhoubing
 * @date 2022-06-12 15:57
 */
public interface StorageService {
    int increaseStorage(int productId, int number);

    int getStorage(int productId);
}
