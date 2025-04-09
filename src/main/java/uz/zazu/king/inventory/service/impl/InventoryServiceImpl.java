package uz.zazu.king.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.zazu.king.inventory.dto.InventoryDto;
import uz.zazu.king.inventory.exception.InventoryNotFoundException;
import uz.zazu.king.inventory.mapper.InventoryMapper;
import uz.zazu.king.inventory.repository.InventoryRepository;
import uz.zazu.king.inventory.service.InventoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public InventoryDto create(InventoryDto inventoryDto) {
        var entity = inventoryMapper.toEntity(inventoryDto);
        var saved = inventoryRepository.save(entity);
        return inventoryMapper.toDto(saved);
    }

    @Override
    public InventoryDto findById(String id) {
        var result = inventoryRepository.findActiveById(id);
        if (result == null) {
            throw new InventoryNotFoundException(id);
        }
        return inventoryMapper.toDto(result);
    }

    @Override
    public List<InventoryDto> findAll() {
        var list = inventoryRepository.findAll();
        return list.stream()
                .map(inventoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventoryDto update(String id, InventoryDto inventoryDto) {
        var existing = inventoryRepository.findActiveById(id);
        if (existing == null) {
            throw new InventoryNotFoundException(id);
        }
        inventoryMapper.updateEntityFromDto(inventoryDto, existing);
        return inventoryMapper.toDto(existing);
    }

    @Override
    @Transactional
    public void delete(String id) {
        var existing = inventoryRepository.findActiveById(id);
        if (existing == null) {
            throw new InventoryNotFoundException(id);
        }
        existing.setActive(false);
        inventoryRepository.save(existing);
    }
}