package uz.zazu.king.service;

import uz.zazu.king.employee.dto.QuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.QuestionnaireDto;
import uz.zazu.king.employee.dto.QuestionnaireEducativeRoleDto;

import java.util.List;

public interface QuestionnaireService {
    QuestionnaireDto create(QuestionnaireDto questionnaireDto);

    QuestionnaireDto findById(String id);

    List<QuestionnaireDto> findAll();

    List<QuestionnaireBusinessRoleDto> findAllBusiness();

    List<QuestionnaireEducativeRoleDto> findAllEducative();

    QuestionnaireDto update(String id, QuestionnaireDto questionnaireDto);

    void remove(String id);
}