package uz.zazu.king.questionnaire.service;

import uz.zazu.king.questionnaire.dto.EmployeeQuestionnaireDto;

import java.util.List;

public interface EmployeeQuestionnaireService {
    EmployeeQuestionnaireDto create(EmployeeQuestionnaireDto dto);

    EmployeeQuestionnaireDto findById(String id);

    List<EmployeeQuestionnaireDto> findAll();

    EmployeeQuestionnaireDto update(String id, EmployeeQuestionnaireDto dto);

    void remove(String id);
}