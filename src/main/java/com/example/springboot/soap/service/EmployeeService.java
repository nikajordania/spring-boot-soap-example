package com.example.springboot.soap.service;

import com.example.springboot.soap.entities.Employee;

public interface EmployeeService {

    void AddEmployee(Employee employee);

    Employee getEmployeeById(long employeeId);

    void updateEmployee(Employee employee);

    void deleteEmployee(long employeeId);
}
