package uz.zazu.king.lead.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.lead.dto.ChildDto;
import uz.zazu.king.lead.service.ChildService;

import java.util.List;

@RestController
@RequestMapping("/api/children")
@PreAuthorize("hasRole('ADMIN')")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public ChildDto create(@RequestBody ChildDto lead) {
        return childService.create(lead);
    }

    @GetMapping("/{id}")
    public ChildDto getById(@PathVariable String id) {
        return childService.findById(id);
    }

    @GetMapping
    public List<ChildDto> getAll() {
        return childService.findAll();
    }

    @PutMapping("/{id}")
    public ChildDto update(@PathVariable String id, @RequestBody ChildDto lead) {
        return childService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        childService.delete(id);
    }
}