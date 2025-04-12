package uz.zazu.king.employee.service;

import uz.zazu.king.employee.dto.EmployeeQuestionnaireBusinessRoleDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireDto;
import uz.zazu.king.employee.dto.EmployeeQuestionnaireEducativeRoleDto;

import java.util.List;

public interface EmployeeQuestionnaireService {
    EmployeeQuestionnaireDto create(EmployeeQuestionnaireDto employeeQuestionnaireDto);

    EmployeeQuestionnaireDto findById(String id);

    List<EmployeeQuestionnaireDto> findAll();

    List<EmployeeQuestionnaireBusinessRoleDto> findAllBusiness();

    List<EmployeeQuestionnaireEducativeRoleDto> findAllEducative();

    EmployeeQuestionnaireDto update(String id, EmployeeQuestionnaireDto employeeQuestionnaireDto);

    void remove(String id);
}