package com.fit.se.service;

import com.fit.se.entity.Department;
import com.fit.se.entity.Employee;
import com.fit.se.repository.EmployeeRepository; // Chỉnh sửa tên repository
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServicelmpl implements EmployeeService { // Đổi tên thành EmployeeServiceImpl

    @Autowired
    private EmployeeRepository employeeRepository; // Chỉnh sửa tên repository
    private RestTemplate restTemplate;

    @Override
    public Employee saveEmployee(Employee employee) {
        ResponseEntity<Department> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/departments/" + employee.getDepartment().getId(),
                        Department.class);
        Department department = responseEntity.getBody();
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) { // Chỉnh sửa tên phương thức
        return employeeRepository.findById(id).orElse(null); // Sử dụng orElse để tránh trả về null nếu không tìm thấy
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployeeById(int id, Employee newEmployee) {
        Employee tempEmployee = employeeRepository.findById(id).orElse(null); // Sử dụng orElse để tránh trả về null nếu không tìm thấy
        if (tempEmployee != null) { // Kiểm tra xem nhân viên có tồn tại không trước khi cập nhật
            tempEmployee.setFirstName(newEmployee.getFirstName());
            tempEmployee.setLastName(newEmployee.getLastName());
            tempEmployee.setAge(newEmployee.getAge());
            tempEmployee.setEmail(newEmployee.getEmail());
            ResponseEntity<Department> responseEntity = restTemplate
                    .getForEntity("http://localhost:8080/departments/" + newEmployee.getDepartment().getId(),
                            Department.class);
            Department department = responseEntity.getBody();
            tempEmployee.setDepartment(department);
            return employeeRepository.save(tempEmployee);
        }
        return null;
    }

}
