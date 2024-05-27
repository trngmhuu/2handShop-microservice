package com.fit.se.controller;

import com.fit.se.entity.Department;
import com.fit.se.repository.DepartmentRedisRepository;
import com.fit.se.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;
    @Autowired
    private DepartmentRedisRepository departmentRedisRepository;

    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        departmentRedisRepository.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        List<Department> departmentList = departmentRedisRepository.findAll();
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id) {
        Department department = departmentRedisRepository.findById(id);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable int id) {
        departmentService.deleteDepartmentById(id);
        departmentRedisRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartmentById(@PathVariable int id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartmentById(id, department);
        departmentRedisRepository.update(updatedDepartment);
        return ResponseEntity.ok(updatedDepartment);
    }
}
