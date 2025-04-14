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
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.service.EmployeeQuestionnaireService;

import java.util.List;

/**
 * Контроллер для работы с данными опросников сотрудников.
 * Поддерживает управление данными опросников для бизнес-ассистентов
 * и менеджеров.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionnaire/employee")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class CandidateProfileController {

    private final EmployeeQuestionnaireService employeeQuestionnaireService;

    @PostMapping
    public ResponseEntity<CandidateProfileDto> create(@RequestBody CandidateProfileDto dto) {
        try {
            var response = employeeQuestionnaireService.create(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateProfileDto> getById(@PathVariable String id) {
        try {
            var response = employeeQuestionnaireService.findById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CandidateProfileDto>> getAll() {
        return ResponseEntity.ok(employeeQuestionnaireService.findAll());
    }

    @GetMapping("/business")
    public ResponseEntity<List<CandidateProfileBusinessDto>> getAllBusiness() {
        return ResponseEntity.ok(employeeQuestionnaireService.findAllBusiness());
    }

    @GetMapping("/educative")
    public ResponseEntity<List<CandidateProfileEducatorDto>> getAllEducative() {
        return ResponseEntity.ok(employeeQuestionnaireService.findAllEducative());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public CandidateProfileDto update(@PathVariable String id, @RequestBody CandidateProfileDto dto) {
        return employeeQuestionnaireService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        employeeQuestionnaireService.remove(id);
    }
}