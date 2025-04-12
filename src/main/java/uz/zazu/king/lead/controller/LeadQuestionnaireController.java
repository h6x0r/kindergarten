package uz.zazu.king.lead.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;
import uz.zazu.king.lead.service.LeadQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/children")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class LeadQuestionnaireController {

    private final LeadQuestionnaireService leadQuestionnaireService;

    @PostMapping
    public LeadQuestionnaireDto create(@RequestBody LeadQuestionnaireDto lead) {
        return leadQuestionnaireService.create(lead);
    }

    @GetMapping("/{id}")
    public LeadQuestionnaireDto getById(@PathVariable String id) {
        return leadQuestionnaireService.findById(id);
    }

    @GetMapping
    public List<LeadQuestionnaireDto> getAll() {
        return leadQuestionnaireService.findAll();
    }

    @PutMapping("/{id}")
    public LeadQuestionnaireDto update(@PathVariable String id, @RequestBody LeadQuestionnaireDto lead) {
        return leadQuestionnaireService.update(id, lead);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        leadQuestionnaireService.delete(id);
    }
}