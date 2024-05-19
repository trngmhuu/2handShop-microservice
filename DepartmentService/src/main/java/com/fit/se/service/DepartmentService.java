package com.fit.se.service;

import com.fit.se.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(int id);

    void deleteDepartmentById(int id);

    Department updateDepartmentById(int id, Department department);

}
