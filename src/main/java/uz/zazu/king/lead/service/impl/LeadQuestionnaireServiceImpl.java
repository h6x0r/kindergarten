package uz.zazu.king.lead.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.zazu.king.lead.dto.LeadQuestionnaireDto;
import uz.zazu.king.lead.entity.QuestionnaireEntity;
import uz.zazu.king.lead.exception.ChildNotFoundException;
import uz.zazu.king.lead.mapper.ChildMapper;
import uz.zazu.king.lead.repository.ChildRepository;
import uz.zazu.king.lead.service.LeadQuestionnaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadQuestionnaireServiceImpl implements LeadQuestionnaireService {

    private final ChildRepository childRepository;
    private final ChildMapper childMapper;

    @Override
    public LeadQuestionnaireDto create(LeadQuestionnaireDto leadQuestionnaireDto) {
        var entity = childMapper.toEntity(leadQuestionnaireDto);
        var saved = childRepository.save(entity);
        return childMapper.toDto(saved);
    }

    @Override
    public LeadQuestionnaireDto findById(String id) {
        var result = childRepository.findActiveById(id);
        if (result == null) {
            throw new ChildNotFoundException(id);
        }
        return childMapper.toDto(result);
    }

    @Override
    public List<LeadQuestionnaireDto> findAll() {
        var list = childRepository.findAll();
        return list.stream()
                .map(childMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LeadQuestionnaireDto update(String id, LeadQuestionnaireDto leadQuestionnaireDto) {
        var existing = childRepository.findActiveById(id);
        if (existing == null) {
            throw new ChildNotFoundException(id);
        }
        childMapper.updateEntityFromDto(leadQuestionnaireDto, existing);
        existing = childRepository.save(existing);
        return childMapper.toDto(existing);
    }

    @Override
    public void delete(String id) {
        QuestionnaireEntity existing = childRepository.findActiveById(id);
        if (existing == null) {
            throw new ChildNotFoundException(id);
        }
        existing.setActive(false);
        childRepository.save(existing);
    }
}