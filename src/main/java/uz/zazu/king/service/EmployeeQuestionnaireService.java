package uz.zazu.king.service;

import uz.zazu.king.employee.dto.EmployeeEmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;

import java.util.List;

public interface EmployeeQuestionnaireService {
    EmployeeQuestionnaireDto create(EmployeeQuestionnaireDto employeeQuestionnaireDto);

    EmployeeQuestionnaireDto findById(String id);

    List<EmployeeQuestionnaireDto> findAll();

    List<EmployeeEmployeeQuestionnaireBusinessRoleDto> findAllBusiness();

    List<EmployeeQuestionnaireEducativeRoleDto> findAllEducative();

    EmployeeQuestionnaireDto update(String id, EmployeeQuestionnaireDto employeeQuestionnaireDto);

    void remove(String id);
}