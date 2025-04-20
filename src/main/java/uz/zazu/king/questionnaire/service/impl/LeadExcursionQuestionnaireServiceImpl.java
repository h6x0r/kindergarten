package uz.zazu.king.questionnaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.LeadExcursionQuestionnaireDto;
import uz.zazu.king.questionnaire.mapper.LeadExcursionQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.LeadExcursionQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.LeadExcursionQuestionnaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadExcursionQuestionnaireServiceImpl implements LeadExcursionQuestionnaireService {

    private final LeadExcursionQuestionnaireRepository repository;
    private final LeadExcursionQuestionnaireMapper mapper;


    @Override
    public LeadExcursionQuestionnaireDto create(LeadExcursionQuestionnaireDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public LeadExcursionQuestionnaireDto findById(String id) {
        var entity = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        return mapper.toDto(entity);
    }

    @Override
    public List<LeadExcursionQuestionnaireDto> findAll() {
        return repository.findAllActive().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeadExcursionQuestionnaireDto update(String id, LeadExcursionQuestionnaireDto dto) {
        var existing = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        mapper.updateEntityFromDto(dto, existing);

        var saved = repository.save(existing);
        return mapper.toDto(saved);
    }

    @Override
    public void remove(String id) {
        var existing = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        existing.setActive(false);
        repository.save(existing);
    }
}