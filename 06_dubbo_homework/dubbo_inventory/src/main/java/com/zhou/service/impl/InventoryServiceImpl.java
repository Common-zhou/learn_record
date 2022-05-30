package com.zhou.service.impl;

import com.zhou.mapper.InventoryMapper;
import com.zhou.model.InventoryDto;
import com.zhou.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoubing
 * @date 2022-05-28 22:51
 */
@DubboService
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper mapper;

    @Override
    public InventoryDto findById(int id) {
        return mapper.findById(id);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean updateInventory(InventoryDto inventoryDto) {
        int affectRow = mapper.update(inventoryDto);
        return affectRow == 1;
    }

    public boolean confirm(InventoryDto dto) {
        log.info("==========confirm method===========");
        int affectRow = mapper.confirm(dto);
        return affectRow == 1;
    }

    public boolean cancel(InventoryDto dto) {
        int affectRow = mapper.cancel(dto);
        return affectRow == 1;
    }

}
