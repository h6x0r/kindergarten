package uz.zazu.king.questionnaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.MarketingQuestionnaireDto;
import uz.zazu.king.questionnaire.mapper.MarketingQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.MarketingQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.MarketingQuestionnaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketingQuestionnaireServiceImpl implements MarketingQuestionnaireService {

    private final MarketingQuestionnaireRepository repository;
    private final MarketingQuestionnaireMapper mapper;


    @Override
    public MarketingQuestionnaireDto create(MarketingQuestionnaireDto dto) {
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public MarketingQuestionnaireDto findById(String id) {
        var entity = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        return mapper.toDto(entity);
    }

    @Override
    public List<MarketingQuestionnaireDto> findAll() {
        return repository.findAllActive().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MarketingQuestionnaireDto update(String id, MarketingQuestionnaireDto dto) {
        var entity = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        mapper.update(entity, dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public void remove(String id) {
        var entity = repository.findActiveById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        entity.setActive(false);
        repository.save(entity);
    }
}