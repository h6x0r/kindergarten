package uz.zazu.king.questionnaire.service;


import uz.zazu.king.questionnaire.dto.ParentChildQuestionnaireDto;
import uz.zazu.king.questionnaire.entity.ParentChildQuestionnaire;

import java.util.List;

public interface ParentChildQuestionnaireService {

    ParentChildQuestionnaire create(ParentChildQuestionnaireDto dto);

    ParentChildQuestionnaire update(String id, ParentChildQuestionnaireDto dto);

    ParentChildQuestionnaire getById(String id);

    List<ParentChildQuestionnaire> getAllActive();

    void delete(String id);
}