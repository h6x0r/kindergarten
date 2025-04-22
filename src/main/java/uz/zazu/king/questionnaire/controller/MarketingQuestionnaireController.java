package uz.zazu.king.questionnaire.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.questionnaire.dto.MarketingQuestionnaireDto;
import uz.zazu.king.questionnaire.service.MarketingQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire/marketing")
public class MarketingQuestionnaireController {

    private final MarketingQuestionnaireService service;

    public MarketingQuestionnaireController(MarketingQuestionnaireService service) {
        this.service = service;
    }

    @PostMapping
    public MarketingQuestionnaireDto create(@RequestBody MarketingQuestionnaireDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public MarketingQuestionnaireDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<MarketingQuestionnaireDto> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public MarketingQuestionnaireDto update(@PathVariable String id, @RequestBody MarketingQuestionnaireDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.remove(id);
    }
}