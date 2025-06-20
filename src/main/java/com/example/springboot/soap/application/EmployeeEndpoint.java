package com.example.springboot.soap.application;

import com.example.springboot.soap.entities.Employee;
import com.example.springboot.soap.interfaces.*;
import com.example.springboot.soap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.example.com";

    private final EmployeeService employeeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        if (request.getEmployeeInfo().getBirthDate() != null) {
            employee.setBirthDate(request.getEmployeeInfo().getBirthDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
        }
        employeeService.AddEmployee(employee);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content Added Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employeeById = employeeService.getEmployeeById(request.getEmployeeId());
        BeanUtils.copyProperties(employeeById, employeeInfo);
        if (employeeById.getBirthDate() != null) {
            try {
                employeeInfo.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(employeeById.getBirthDate().toString()));
            } catch (DatatypeConfigurationException ignored) {
            }
        }
        response.setEmployeeInfo(employeeInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByEmailRequest")
    @ResponsePayload
    public GetEmployeeByEmailResponse getEmployeeByEmail(@RequestPayload GetEmployeeByEmailRequest request) {
        GetEmployeeByEmailResponse response = new GetEmployeeByEmailResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employeeByEmail = employeeService.getEmployeeByEmail(request.getEmail());
        BeanUtils.copyProperties(employeeByEmail, employeeInfo);
        if (employeeByEmail.getBirthDate() != null) {
            try {
                employeeInfo.setBirthDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(employeeByEmail.getBirthDate().toString()));
            } catch (DatatypeConfigurationException ignored) {
            }
        }
        response.setEmployeeInfo(employeeInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        if (request.getEmployeeInfo().getBirthDate() != null) {
            employee.setBirthDate(request.getEmployeeInfo().getBirthDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
        }
        employeeService.updateEmployee(employee);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content Updated Successfully");
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        employeeService.deleteEmployee(request.getEmployeeId());
        ServiceStatus serviceStatus = new ServiceStatus();

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Content Deleted Successfully");
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

}
