package uz.zazu.king.employee.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;
import uz.zazu.king.employee.service.CandidateProfileService;

import java.util.List;

/**
 * Контроллер для работы с данными опросников сотрудников.
 * Поддерживает управление данными опросников для бизнес-ассистентов
 * и менеджеров.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidate")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    @PostMapping
    public ResponseEntity<CandidateProfileDto> create(@NotNull @RequestBody CandidateProfileDto dto) {
        try {
            var response = candidateProfileService.create(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateProfileDto> getById(@NotBlank @PathVariable String id) {
        try {
            var response = candidateProfileService.findById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CandidateProfileDto>> getAll() {
        return ResponseEntity.ok(candidateProfileService.findAll());
    }

    @GetMapping("/business")
    public ResponseEntity<List<CandidateProfileBusinessDto>> getAllBusiness() {
        return ResponseEntity.ok(candidateProfileService.findAllBusiness());
    }

    @GetMapping("/educative")
    public ResponseEntity<List<CandidateProfileEducatorDto>> getAllEducative() {
        return ResponseEntity.ok(candidateProfileService.findAllEducative());
    }

    @GetMapping("/nanny")
    public ResponseEntity<List<CandidateProfileNannyDto>> getAllNanny() {
        return ResponseEntity.ok(candidateProfileService.findAllNanny());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public CandidateProfileDto update(@NotBlank @PathVariable String id, @Valid @RequestBody CandidateProfileDto dto) {
        return candidateProfileService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@NotBlank @PathVariable String id) {
        candidateProfileService.remove(id);
    }
}