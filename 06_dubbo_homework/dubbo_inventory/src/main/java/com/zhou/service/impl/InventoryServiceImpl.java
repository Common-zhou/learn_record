package com.zhou.service.impl;

import com.zhou.mapper.InventoryMapper;
import com.zhou.model.InventoryDto;
import com.zhou.service.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoubing
 * @date 2022-05-28 22:51
 */
@DubboService
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper mapper;

    @Override
    public InventoryDto findById(int id) {
        return mapper.findById(id);
    }
}
