package uz.zazu.king.employee.service;

import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;

import java.util.List;

public interface CandidateProfileService {
    CandidateProfileDto create(CandidateProfileDto candidateProfileDto);

    CandidateProfileDto findById(String id);

    List<CandidateProfileDto> findAll();

    List<CandidateProfileBusinessDto> findAllBusiness();

    List<CandidateProfileBusinessDto> findAllBusinessByManager();

    List<CandidateProfileBusinessDto> findAllByBusinessAssistant();

    List<CandidateProfileEducatorDto> findAllEducative();

    List<CandidateProfileNannyDto> findAllNanny();

    CandidateProfileDto update(String id, CandidateProfileDto candidateProfileDto);

    void remove(String id);
}