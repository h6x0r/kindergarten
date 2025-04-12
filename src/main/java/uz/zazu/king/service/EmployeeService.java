package uz.zazu.king.service;

import uz.zazu.king.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);

    EmployeeDto findById(String id);

    List<EmployeeDto> findAll();

    EmployeeDto update(String id, EmployeeDto employeeDto);

    void remove(String id);
}