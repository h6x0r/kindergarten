package uz.zazu.king.questionnaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.questionnaire.mapper.EmployeeQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.EmployeeQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.EmployeeQuestionnaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeQuestionnaireServiceImpl implements EmployeeQuestionnaireService {

    private final EmployeeQuestionnaireRepository repository;
    private final EmployeeQuestionnaireMapper mapper;

    @Override
    public EmployeeQuestionnaireDto create(EmployeeQuestionnaireDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public EmployeeQuestionnaireDto findById(String id) {
        var entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        return mapper.toDto(entity);
    }

    @Override
    public List<EmployeeQuestionnaireDto> findAll() {
        return repository.findAllByIsActiveTrue()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeQuestionnaireDto update(String id, EmployeeQuestionnaireDto dto) {
        var entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));

        mapper.updateFromDto(dto, entity);

        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void remove(String id) {
        var entity = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));

        entity.setActive(false);
        repository.save(entity);
    }
}