package uz.zazu.king.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.zazu.king.employee.dto.EmployeeDto;
import uz.zazu.king.employee.service.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
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