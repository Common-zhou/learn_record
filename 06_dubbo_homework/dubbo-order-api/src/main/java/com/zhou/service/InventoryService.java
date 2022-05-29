package com.zhou.service;

import com.zhou.model.InventoryDto;

/**
 * @author zhoubing
 * @date 2022-05-28 22:51
 */
public interface InventoryService {
    InventoryDto findById(int id);

    boolean updateInventory(InventoryDto inventoryDto);
}
