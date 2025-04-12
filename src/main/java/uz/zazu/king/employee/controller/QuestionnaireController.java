package uz.zazu.king.employee.controller;

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
import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;
import uz.zazu.king.service.QuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @PostMapping
    public ResponseEntity<QuestionnaireDto> create(@RequestBody QuestionnaireDto dto) {
        try {
            var response = questionnaireService.create(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaireDto> getById(@PathVariable String id) {
        try {
            var response = questionnaireService.findById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<QuestionnaireDto>> getAll() {
        return ResponseEntity.ok(questionnaireService.findAll());
    }

    @GetMapping("/business")
    public ResponseEntity<List<QuestionnaireBusinessRoleDto>> getAllBusiness() {
        return ResponseEntity.ok(questionnaireService.findAllBusiness());
    }

    @GetMapping("/educative")
    public ResponseEntity<List<QuestionnaireEducativeRoleDto>> getAllEducative() {
        return ResponseEntity.ok(questionnaireService.findAllEducative());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public QuestionnaireDto update(@PathVariable String id, @RequestBody QuestionnaireDto dto) {
        return questionnaireService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        questionnaireService.remove(id);
    }
}