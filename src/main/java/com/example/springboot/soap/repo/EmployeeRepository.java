package com.example.springboot.soap.repo;

import com.example.springboot.soap.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {


    Employee findByEmployeeId(long employeeId);

}
