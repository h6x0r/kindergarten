package uz.zazu.king.questionnaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.common.exception.QuestionnaireNotFoundException;
import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;
import uz.zazu.king.questionnaire.mapper.LeadQuestionnaireMapper;
import uz.zazu.king.questionnaire.repository.LeadQuestionnaireRepository;
import uz.zazu.king.questionnaire.service.LeadQuestionnaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadQuestionnaireServiceImpl implements LeadQuestionnaireService {

    private final LeadQuestionnaireRepository leadQuestionnaireRepository;
    private final LeadQuestionnaireMapper leadQuestionnaireMapper;

    @Override
    public LeadQuestionnaireDto create(LeadQuestionnaireDto leadQuestionnaireDto) {
        var entity = leadQuestionnaireMapper.toEntity(leadQuestionnaireDto);
        var saved = leadQuestionnaireRepository.save(entity);
        return leadQuestionnaireMapper.toDto(saved);
    }

    @Override
    public LeadQuestionnaireDto findById(String id) {
        var result = leadQuestionnaireRepository.findActiveById(id);
        if (result != null) {
            return leadQuestionnaireMapper.toDto(result);
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public List<LeadQuestionnaireDto> findAll() {
        var list = leadQuestionnaireRepository.findAllActive();
        return list.stream()
                .map(leadQuestionnaireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeadQuestionnaireDto update(String id, LeadQuestionnaireDto leadQuestionnaireDto) {
        var existing = leadQuestionnaireRepository.findActiveById(id);
        if (existing != null) {
            leadQuestionnaireMapper.updateEntityFromDto(leadQuestionnaireDto, existing);
            existing = leadQuestionnaireRepository.save(existing);
            return leadQuestionnaireMapper.toDto(existing);
        }

        throw new QuestionnaireNotFoundException(id);
    }

    @Override
    public void delete(String id) {
        var existing = leadQuestionnaireRepository.findActiveById(id);
        if (existing != null) {
            existing.setActive(false);
            leadQuestionnaireRepository.save(existing);
            return;
        }

        throw new QuestionnaireNotFoundException(id);
    }
}