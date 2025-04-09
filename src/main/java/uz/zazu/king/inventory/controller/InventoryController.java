package uz.zazu.king.inventory.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.inventory.dto.InventoryDto;
import uz.zazu.king.inventory.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@PreAuthorize("hasRole('ADMIN')")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public InventoryDto create(@RequestBody InventoryDto inventory) {
        return inventoryService.create(inventory);
    }

    @GetMapping("/{id}")
    public InventoryDto getById(@PathVariable String id) {
        return inventoryService.findById(id);
    }

    @GetMapping
    public List<InventoryDto> getAll() {
        return inventoryService.findAll();
    }

    @PutMapping("/{id}")
    public InventoryDto update(@PathVariable String id, @RequestBody InventoryDto inventory) {
        return inventoryService.update(id, inventory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        inventoryService.delete(id);
    }
}