package uz.zazu.king.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.questionnaire.dto.ParentChildQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;
import uz.zazu.king.questionnaire.service.ParentChildQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire/parent-child")
@RequiredArgsConstructor
public class ParentChildQuestionnaireController {

    private final ParentChildQuestionnaireService service;

    @PostMapping
    public ParentChildQuestionnaire create(@RequestBody ParentChildQuestionnaireDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ParentChildQuestionnaire update(@PathVariable String id,
                                           @RequestBody ParentChildQuestionnaireDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public ParentChildQuestionnaire getById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ParentChildQuestionnaire> getAllActive() {
        return service.getAllActive();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}