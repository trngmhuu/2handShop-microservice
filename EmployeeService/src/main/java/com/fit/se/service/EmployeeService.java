package com.fit.se.service;

import com.fit.se.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployee();

    void deleteEmployeeById(int id);

    Employee updateEmployeeById(int id, Employee newEmployee);
}
