package uz.zazu.king.questionnaire.service;


import uz.zazu.king.questionnaire.dto.MarketingQuestionnaireDto;

import java.util.List;

public interface MarketingQuestionnaireService {

    MarketingQuestionnaireDto create(MarketingQuestionnaireDto dto);

    MarketingQuestionnaireDto findById(String id);

    List<MarketingQuestionnaireDto> findAll();

    MarketingQuestionnaireDto update(String id, MarketingQuestionnaireDto dto);

    void remove(String id);
}