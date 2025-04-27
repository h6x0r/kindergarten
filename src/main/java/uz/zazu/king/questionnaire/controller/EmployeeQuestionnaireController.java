package uz.zazu.king.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.questionnaire.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.questionnaire.service.EmployeeQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire/employee")
@RequiredArgsConstructor
public class EmployeeQuestionnaireController {

    private final EmployeeQuestionnaireService service;

    @PostMapping
    public EmployeeQuestionnaireDto create(@RequestBody EmployeeQuestionnaireDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public EmployeeQuestionnaireDto getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public List<EmployeeQuestionnaireDto> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public EmployeeQuestionnaireDto update(@PathVariable String id, @RequestBody EmployeeQuestionnaireDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.remove(id);
    }
}