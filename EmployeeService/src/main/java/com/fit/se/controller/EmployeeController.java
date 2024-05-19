package com.fit.se.controller;


import com.fit.se.entity.Employee;
import com.fit.se.repository.EmployeeRedisRepository;
import com.fit.se.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    private EmployeeRedisRepository employeeRedisRepository;


    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        employeeRedisRepository.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employeeList = employeeRedisRepository.findAll();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int employeeId) {
        Employee employee = employeeRedisRepository.findById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        employeeRedisRepository.deleteById(employeeId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployeeById(@PathVariable("id") int employeeId, @RequestBody Employee newEmployee) {
        Employee updatedEmployee = employeeService.updateEmployeeById(employeeId, newEmployee);
        employeeRedisRepository.update(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }





}
