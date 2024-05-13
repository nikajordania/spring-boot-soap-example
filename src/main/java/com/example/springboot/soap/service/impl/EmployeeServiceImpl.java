package com.example.springboot.soap.service.impl;

import com.example.springboot.soap.entities.Employee;
import com.example.springboot.soap.repo.EmployeeRepository;
import com.example.springboot.soap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void AddEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

    @Override
    public void deleteEmployee(long employeeId) {
        employeeRepository.deleteById(employeeId);

    }
}
