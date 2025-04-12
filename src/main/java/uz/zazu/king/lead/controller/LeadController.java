package uz.zazu.king.lead.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
@RequestMapping("/api/leads")
@PreAuthorize("hasAnyRole('SUPER_ADMIN')")
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public LeadDto create(@Valid @RequestBody LeadDto lead) {
        return leadService.create(lead);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDto> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(leadService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<LeadDto> getAll() {
        return leadService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadDto> update(@PathVariable String id, @RequestBody LeadDto lead) {
        try {
            return ResponseEntity.ok(leadService.update(id, lead));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            leadService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}