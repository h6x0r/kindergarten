package uz.zazu.king.inventory.service;

import uz.zazu.king.inventory.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    InventoryDto create(InventoryDto inventoryDto);

    InventoryDto findById(String id);

    List<InventoryDto> findAll();

    InventoryDto update(String id, InventoryDto inventoryDto);

    void delete(String id);
}