package uz.zazu.king.questionnaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.ParentChildQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;
import uz.zazu.king.questionnaire.mapper.ParentChildQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.ParentChildQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.ParentChildQuestionnaireService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentChildQuestionnaireServiceImpl implements ParentChildQuestionnaireService {

    private final ParentChildQuestionnaireRepository repository;
    private final ParentChildQuestionnaireMapper mapper;

    @Override
    public ParentChildQuestionnaire create(ParentChildQuestionnaireDto dto) {
        ParentChildQuestionnaire entity = mapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public ParentChildQuestionnaire update(String id, ParentChildQuestionnaireDto dto) {
        ParentChildQuestionnaire existing = repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        mapper.update(dto, existing);
        return repository.save(existing);
    }

    @Override
    public ParentChildQuestionnaire getById(String id) {
        return repository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
    }

    @Override
    public List<ParentChildQuestionnaire> getAllActive() {
        return repository.findAllByIsActiveTrue();
    }

    @Override
    public void delete(String id) {
        var existing = repository.findById(id)
                .orElseThrow(() -> new QuestionnaireNotFoundException(id));
        existing.setIsActive(false);
        repository.save(existing);
    }
}