package uz.zazu.king.employee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.employee.dto.CandidateProfileBusinessDto;
import uz.zazu.king.employee.dto.CandidateProfileDto;
import uz.zazu.king.employee.dto.CandidateProfileEducatorDto;
import uz.zazu.king.employee.dto.CandidateProfileNannyDto;
import uz.zazu.king.employee.mapper.CandidateProfileMapper;
import uz.zazu.king.employee.repository.CandidateProfileBusinessRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileEducatorRoleRepository;
import uz.zazu.king.employee.repository.CandidateProfileNannyRoleRepository;
import uz.zazu.king.employee.service.CandidateProfileService;
import uz.zazu.king.info.enums.Module;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateProfileServiceImpl implements CandidateProfileService {

    private final CandidateProfileMapper candidateProfileMapper;
    private final CandidateProfileBusinessRoleRepository businessRoleRepository;
    private final CandidateProfileEducatorRoleRepository educativeRoleRepository;
    private final CandidateProfileNannyRoleRepository nannyRoleRepository;

    @Override
    public CandidateProfileDto create(CandidateProfileDto candidateProfileDto) {
        if (candidateProfileDto instanceof CandidateProfileBusinessDto dto) {
            var entity = candidateProfileMapper.toBusinessRoleEntity(dto);
            var saved = businessRoleRepository.save(entity);
            return candidateProfileMapper.toBusinessRoleDto(saved);

        } else if (candidateProfileDto instanceof CandidateProfileEducatorDto dto) {
            var entity = candidateProfileMapper.toEducativeRoleEntity(dto);
            entity = educativeRoleRepository.save(entity);
            return candidateProfileMapper.toEducativeRoleDto(entity);
        } else if (candidateProfileDto instanceof CandidateProfileNannyDto dto) {
            var entity = candidateProfileMapper.toNannyRoleEntity(dto);
            entity = nannyRoleRepository.save(entity);
            return candidateProfileMapper.toNannyRoleDto(entity);
        }

        throw new IllegalArgumentException("Неизвестный тип DTO для создания записи.");
    }

    @Override
    public CandidateProfileDto findById(String id) {
        var business = businessRoleRepository.findByIdAndIsActiveTrue(id);
        if (business.isPresent()) {
            return candidateProfileMapper.toBusinessRoleDto(business.get());
        }

        var educative = educativeRoleRepository.findByIdAndIsActiveTrue(id);
        if (educative.isPresent()) {
            return candidateProfileMapper.toEducativeRoleDto(educative.get());
        }

        var nanny = nannyRoleRepository.findByIdAndIsActiveTrue(id);
        if (nanny.isPresent()) {
            return candidateProfileMapper.toNannyRoleDto(nanny.get());
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public List<CandidateProfileDto> findAll() {
        var businessList = businessRoleRepository.findAll();
        var convertedBusinessList = businessList.stream()
                .map(candidateProfileMapper::toBusinessRoleDto)
                .toList();

        var results = new ArrayList<CandidateProfileDto>(convertedBusinessList);

        var educativeList = educativeRoleRepository.findAll();
        var convertedEducativeList = educativeList.stream()
                .map(candidateProfileMapper::toEducativeRoleDto)
                .toList();

        results.addAll(convertedEducativeList);

        return results;
    }

    @Override
    public List<CandidateProfileBusinessDto> findAllBusiness() {
        var businessList = businessRoleRepository.findAllByIsActiveTrue();
        return businessList.stream()
                .map(candidateProfileMapper::toBusinessRoleDto)
                .toList();
    }

    @Override
    public List<CandidateProfileBusinessDto> findAllByBusinessAssistant() {
        var businessList = businessRoleRepository.findAllByIsActiveTrue();
        return businessList.stream()
                .filter(a -> a.getModule() == Module.BUSINESS_ASSISTANT)
                .map(candidateProfileMapper::toBusinessRoleDto)
                .toList();
    }

    @Override
    public List<CandidateProfileBusinessDto> findAllBusinessByManager() {
        var businessList = businessRoleRepository.findAllByIsActiveTrue();
        return businessList.stream()
                .filter(a -> a.getModule() == Module.MANAGER)
                .map(candidateProfileMapper::toBusinessRoleDto)
                .toList();
    }

    @Override
    public List<CandidateProfileEducatorDto> findAllEducative() {
        var educativeList = educativeRoleRepository.findAllByIsActiveTrue();
        return educativeList.stream()
                .map(candidateProfileMapper::toEducativeRoleDto)
                .toList();
    }

    @Override
    public List<CandidateProfileNannyDto> findAllNanny() {
        var educativeList = nannyRoleRepository.findAllByIsActiveTrue();
        return educativeList.stream()
                .map(candidateProfileMapper::toNannyRoleDto)
                .toList();
    }

    @Override
    public CandidateProfileDto update(String id, CandidateProfileDto candidateProfileDto) {
        if (candidateProfileDto instanceof CandidateProfileBusinessDto dto) {
            var existingEntity = businessRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
            if (existingEntity != null) {
                candidateProfileMapper.updateBusinessRoleEntityFromDto(dto, existingEntity);
                var updatedEntity = businessRoleRepository.save(existingEntity);
                return candidateProfileMapper.toBusinessRoleDto(updatedEntity);
            }
        } else if (candidateProfileDto instanceof CandidateProfileEducatorDto dto) {
            var existingEntity = educativeRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
            if (existingEntity != null) {
                candidateProfileMapper.updateEducativeRoleEntityFromDto(dto, existingEntity);
                var updatedEntity = educativeRoleRepository.save(existingEntity);
                return candidateProfileMapper.toEducativeRoleDto(updatedEntity);
            }
        } else if (candidateProfileDto instanceof CandidateProfileNannyDto dto) {
            var existingEntity = nannyRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
            if (existingEntity != null) {
                candidateProfileMapper.updateNannyRoleEntityFromDto(dto, existingEntity);
                var updatedEntity = nannyRoleRepository.save(existingEntity);
                return candidateProfileMapper.toNannyRoleDto(updatedEntity);
            }
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public void remove(String id) {
        var business = businessRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if (business != null) {
            business.setActive(false);
            businessRoleRepository.save(business);
            return;
        }

        var educative = educativeRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if (educative != null) {
            educative.setActive(false);
            educativeRoleRepository.save(educative);
            return;
        }

        var nanny = nannyRoleRepository.findByIdAndIsActiveTrue(id).orElse(null);
        if (nanny != null) {
            nanny.setActive(false);
            nannyRoleRepository.save(nanny);
            return;
        }

        throw new QuestionnaireNotFoundException(id);
    }
}