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
import uz.zazu.king.lead.dto.LeadDto;
import uz.zazu.king.lead.service.LeadService;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@PreAuthorize("hasRole('ADMIN')")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public LeadDto create(@RequestBody LeadDto lead) {
        return leadService.create(lead);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public LeadDto getById(@PathVariable String id) {
        return leadService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<LeadDto> getAll() {
        return leadService.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public LeadDto update(@PathVariable String id, @RequestBody LeadDto lead) {
        return leadService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable String id) {
        leadService.delete(id);
    }
}