package uz.zazu.king.employee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;
import uz.zazu.king.employee.mapper.EmployeeQuestionnaireMapper;
import uz.zazu.king.employee.repository.EmployeeQuestionnaireBusinessRoleRepository;
import uz.zazu.king.employee.repository.EmployeeQuestionnaireEducativeRoleRepository;
import uz.zazu.king.employee.service.EmployeeQuestionnaireService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeEmployeeQuestionnaireServiceImpl implements EmployeeQuestionnaireService {

    private final EmployeeQuestionnaireMapper employeeQuestionnaireMapper;
    private final EmployeeQuestionnaireBusinessRoleRepository businessRoleRepository;
    private final EmployeeQuestionnaireEducativeRoleRepository educativeRoleRepository;

    @Override
    public EmployeeQuestionnaireDto create(EmployeeQuestionnaireDto employeeQuestionnaireDto) {
        Assert.notNull(employeeQuestionnaireDto, "обьект не может быть null");

        if (employeeQuestionnaireDto instanceof EmployeeQuestionnaireBusinessRoleDto dto) {
            var entity = employeeQuestionnaireMapper.toBusinessRoleEntity(dto);
            var saved = businessRoleRepository.save(entity);
            return employeeQuestionnaireMapper.toBusinessRoleDto(saved);

        } else if (employeeQuestionnaireDto instanceof EmployeeQuestionnaireEducativeRoleDto dto) {
            var entity = employeeQuestionnaireMapper.toEducativeRoleEntity(dto);
            entity = educativeRoleRepository.save(entity);
            return employeeQuestionnaireMapper.toEducativeRoleDto(entity);
        }

        throw new IllegalArgumentException("Неизвестный тип DTO для создания записи.");
    }

    @Override
    public EmployeeQuestionnaireDto findById(String id) {
        Assert.hasText(id, "id не может быть пустым");

        var business = businessRoleRepository.findByIdAndIsActiveTrue(id);
        if (business != null) {
            return employeeQuestionnaireMapper.toBusinessRoleDto(business);
        }

        var educative = educativeRoleRepository.findByIdAndIsActiveTrue(id);
        if (educative != null) {
            return employeeQuestionnaireMapper.toEducativeRoleDto(educative);
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public List<EmployeeQuestionnaireDto> findAll() {
        var businessList = businessRoleRepository.findAll();
        var convertedBusinessList = businessList.stream()
                .map(employeeQuestionnaireMapper::toBusinessRoleDto)
                .toList();

        var results = new ArrayList<EmployeeQuestionnaireDto>(convertedBusinessList);

        var educativeList = educativeRoleRepository.findAll();
        var convertedEducativeList = educativeList.stream()
                .map(employeeQuestionnaireMapper::toEducativeRoleDto)
                .toList();

        results.addAll(convertedEducativeList);

        return results;
    }

    @Override
    public List<EmployeeQuestionnaireBusinessRoleDto> findAllBusiness() {
        var businessList = businessRoleRepository.findAllByIsActiveTrue();
        return businessList.stream()
                .map(employeeQuestionnaireMapper::toBusinessRoleDto)
                .toList();
    }

    @Override
    public List<EmployeeQuestionnaireEducativeRoleDto> findAllEducative() {
        var educativeList = educativeRoleRepository.findAllByIsActiveTrue();
        return educativeList.stream()
                .map(employeeQuestionnaireMapper::toEducativeRoleDto)
                .toList();
    }

    @Override
    public EmployeeQuestionnaireDto update(String id, EmployeeQuestionnaireDto employeeQuestionnaireDto) {
        Assert.hasText(id, "id не может быть пустым");
        Assert.notNull(employeeQuestionnaireDto, "обьект не может быть пустым");

        if (employeeQuestionnaireDto instanceof EmployeeQuestionnaireBusinessRoleDto dto) {
            var existingEntity = businessRoleRepository.findByIdAndIsActiveTrue(id);
            if (existingEntity != null) {
                employeeQuestionnaireMapper.updateBusinessRoleEntityFromDto(dto, existingEntity);
                var updatedEntity = businessRoleRepository.save(existingEntity);
                return employeeQuestionnaireMapper.toBusinessRoleDto(updatedEntity);
            }
        } else if (employeeQuestionnaireDto instanceof EmployeeQuestionnaireEducativeRoleDto dto) {
            var existingEntity = educativeRoleRepository.findByIdAndIsActiveTrue(id);
            if (existingEntity != null) {
                employeeQuestionnaireMapper.updateEducativeRoleEntityFromDto(dto, existingEntity);
                var updatedEntity = educativeRoleRepository.save(existingEntity);
                return employeeQuestionnaireMapper.toEducativeRoleDto(updatedEntity);
            }
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public void remove(String id) {
        Assert.hasText(id, "id не может быть пустым");

        var business = businessRoleRepository.findByIdAndIsActiveTrue(id);
        if (business != null) {
            business.setActive(false);
            businessRoleRepository.save(business);
            return;
        }

        var educative = educativeRoleRepository.findByIdAndIsActiveTrue(id);
        if (educative != null) {
            educative.setActive(false);
            educativeRoleRepository.save(educative);
            return;
        }

        throw new QuestionnaireNotFoundException(id);
    }
}