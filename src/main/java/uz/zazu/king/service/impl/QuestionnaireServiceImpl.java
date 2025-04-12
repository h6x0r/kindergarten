package uz.zazu.king.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;
import uz.zazu.king.employee.entity.QuestionnaireEducativeRoleEntity;
import uz.zazu.king.employee.mapper.QuestionnaireMapper;
import uz.zazu.king.employee.repository.QuestionnaireBusinessRoleRepository;
import uz.zazu.king.employee.repository.QuestionnaireEducativeRoleRepository;
import uz.zazu.king.service.QuestionnaireService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private final QuestionnaireMapper questionnaireMapper;
    private final QuestionnaireBusinessRoleRepository businessRoleRepository;
    private final QuestionnaireEducativeRoleRepository educativeRoleRepository;

    @Override
    public QuestionnaireDto create(QuestionnaireDto questionnaireDto) {
        Assert.notNull(questionnaireDto, "questionnaireDto не может быть null");

        if (questionnaireDto instanceof QuestionnaireBusinessRoleDto dto) {
            var entity = questionnaireMapper.toBusinessRoleEntity(dto);
            var saved = businessRoleRepository.save(entity);
            return questionnaireMapper.toBusinessRoleDto(saved);

        } else if (questionnaireDto instanceof QuestionnaireEducativeRoleDto dto) {
            var entity = questionnaireMapper.toEducativeRoleEntity(dto);
            entity = educativeRoleRepository.save(entity);
            return questionnaireMapper.toEducativeRoleDto(entity);
        }

        throw new IllegalArgumentException("Неизвестный тип DTO для создания записи.");
    }

    @Override
    public QuestionnaireDto findById(String id) {
        Assert.hasText(id, "id не может быть пустым");

        var businessOpt = businessRoleRepository.findById(id);
        if (businessOpt.isPresent()) {
            return questionnaireMapper.toBusinessRoleDto(businessOpt.get());
        }

        var educativeOpt = educativeRoleRepository.findById(id);
        if (educativeOpt.isPresent()) {
            return questionnaireMapper.toEducativeRoleDto(educativeOpt.get());
        }

        throw new IllegalArgumentException("Опросник с указанным ID не найден.");
    }

    @Override
    public List<QuestionnaireDto> findAll() {
        var businessList = businessRoleRepository.findAll();
        var convertedBusinessList = businessList.stream()
                .map(questionnaireMapper::toBusinessRoleDto)
                .toList();

        var results = new ArrayList<QuestionnaireDto>(convertedBusinessList);

        var educativeList = educativeRoleRepository.findAll();
        var convertedEducativeList = educativeList.stream()
                .map(questionnaireMapper::toEducativeRoleDto)
                .toList();

        results.addAll(convertedEducativeList);

        return results;
    }

    @Override
    public List<QuestionnaireBusinessRoleDto> findAllBusiness() {
        var businessList = businessRoleRepository.findAllByIsActiveTrue();
        return businessList.stream()
                .map(questionnaireMapper::toBusinessRoleDto)
                .toList();
    }

    @Override
    public List<QuestionnaireEducativeRoleDto> findAllEducative() {
        var educativeList = educativeRoleRepository.findAllByIsActiveTrue();
        return educativeList.stream()
                .map(questionnaireMapper::toEducativeRoleDto)
                .toList();
    }

    @Override
    public QuestionnaireDto update(String id, QuestionnaireDto questionnaireDto) {
        Assert.hasText(id, "id не может быть пустым");
        Assert.notNull(questionnaireDto, "questionnaireDto не может быть null");

        if (questionnaireDto instanceof QuestionnaireBusinessRoleDto dto) {
            var existingEntity =
                    businessRoleRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Бизнес-опросник не найден"));
            questionnaireMapper.updateBusinessRoleEntityFromDto(dto, existingEntity);
            var updatedEntity = businessRoleRepository.save(existingEntity);
            return questionnaireMapper.toBusinessRoleDto(updatedEntity);

        } else if (questionnaireDto instanceof QuestionnaireEducativeRoleDto dto) {
            QuestionnaireEducativeRoleEntity existingEntity =
                    educativeRoleRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Образовательный опросник не найден"));
            questionnaireMapper.updateEducativeRoleEntityFromDto(dto, existingEntity);
            var updatedEntity = educativeRoleRepository.save(existingEntity);
            return questionnaireMapper.toEducativeRoleDto(updatedEntity);
        }

        throw new IllegalArgumentException("Неизвестный тип DTO для обновления записи.");
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

        throw new IllegalArgumentException("Опросник с указанным ID не найден для удаления.");
    }
}