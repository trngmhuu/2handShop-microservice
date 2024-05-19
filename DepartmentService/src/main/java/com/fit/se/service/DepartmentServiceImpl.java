package com.fit.se.service;

import com.fit.se.entity.Department;
import com.fit.se.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public void deleteDepartmentById(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartmentById(int id, Department department) {
        Department tempDepartment = departmentRepository.findById(id).get();
        tempDepartment.setDepartmentName(department.getDepartmentName());
        tempDepartment.setDepartmentAddress(department.getDepartmentAddress());
        tempDepartment.setDepartmentCode(department.getDepartmentCode());
        return departmentRepository.save(tempDepartment);
    }
}
