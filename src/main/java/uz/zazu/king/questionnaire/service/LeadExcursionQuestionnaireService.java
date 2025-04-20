package uz.zazu.king.questionnaire.service;

import uz.zazu.king.questionnaire.dto.LeadExcursionQuestionnaireDto;

import java.util.List;

public interface LeadExcursionQuestionnaireService {

    LeadExcursionQuestionnaireDto create(LeadExcursionQuestionnaireDto dto);

    LeadExcursionQuestionnaireDto findById(String id);

    List<LeadExcursionQuestionnaireDto> findAll();

    LeadExcursionQuestionnaireDto update(String id, LeadExcursionQuestionnaireDto dto);

    void remove(String id);
}