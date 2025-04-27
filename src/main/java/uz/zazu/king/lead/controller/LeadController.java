package uz.zazu.king.lead.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.lead.dto.LeadDto;
import uz.zazu.king.lead.service.LeadService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public LeadDto create(@Valid @RequestBody LeadDto lead) {
        return leadService.create(lead);
    }

    @GetMapping("/{id}")
    public LeadDto getById(@PathVariable String id) {
        return leadService.findById(id);
    }

    @GetMapping
    public List<LeadDto> getAll() {
        return leadService.findAll();
    }

    @PutMapping("/{id}")
    public LeadDto update(@PathVariable String id, @RequestBody LeadDto lead) {
        return leadService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        leadService.delete(id);
    }
}