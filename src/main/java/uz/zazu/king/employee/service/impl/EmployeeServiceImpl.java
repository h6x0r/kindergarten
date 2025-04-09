package uz.zazu.king.employee.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.zazu.king.employee.common.exception.EmployeeNotFoundException;
import uz.zazu.king.employee.dto.EmployeeDto;
import uz.zazu.king.employee.mapper.EmployeeMapper;
import uz.zazu.king.employee.repository.EmployeeRepository;
import uz.zazu.king.employee.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        var entity = employeeMapper.toEntity(employeeDto);
        var saved = employeeRepository.save(entity);
        return employeeMapper.toDto(saved);
    }

    @Override
    public EmployeeDto findById(String id) {
        var result = employeeRepository.findActiveById(id);
        if (result == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employeeMapper.toDto(result);
    }

    @Override
    public List<EmployeeDto> findAll() {
        var list = employeeRepository.findAll();
        return list.stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDto update(String id, EmployeeDto employeeDto) {
        var existing = employeeRepository.findActiveById(id);
        if (existing == null) {
            throw new EmployeeNotFoundException(id);
        }
        employeeMapper.updateEntityFromDto(employeeDto, existing);
        return employeeMapper.toDto(existing);
    }

    @Override
    @Transactional
    public void remove(String id) {
        var existing = employeeRepository.findActiveById(id);
        if (existing == null) {
            throw new EmployeeNotFoundException(id);
        }
        existing.setActive(false);
        employeeRepository.save(existing);
    }
}