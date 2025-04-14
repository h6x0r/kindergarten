package uz.zazu.king.questionnaire.service;

import uz.zazu.king.questionnaire.dto.LeadQuestionnaireDto;

import java.util.List;

public interface LeadQuestionnaireService {
    LeadQuestionnaireDto create(LeadQuestionnaireDto leadQuestionnaireDto);

    LeadQuestionnaireDto findById(String id);

    List<LeadQuestionnaireDto> findAll();

    LeadQuestionnaireDto update(String id, LeadQuestionnaireDto leadQuestionnaireDto);

    void delete(String id);
}