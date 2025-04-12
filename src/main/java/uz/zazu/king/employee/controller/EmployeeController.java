package uz.zazu.king.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.zazu.king.employee.dto.EmployeeDto;
import uz.zazu.king.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto employee) {
        return employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.findAll();
    }

    @PutMapping("/{id}")
    public EmployeeDto update(@PathVariable String id, @RequestBody EmployeeDto employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable String id) {
        employeeService.remove(id);
    }
}