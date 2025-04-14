package uz.zazu.king.employee.service;

import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;

import java.util.List;

public interface EmployeeQuestionnaireService {
    CandidateProfileDto create(CandidateProfileDto candidateProfileDto);

    CandidateProfileDto findById(String id);

    List<CandidateProfileDto> findAll();

    List<CandidateProfileBusinessDto> findAllBusiness();

    List<CandidateProfileEducatorDto> findAllEducative();

    CandidateProfileDto update(String id, CandidateProfileDto candidateProfileDto);

    void remove(String id);
}