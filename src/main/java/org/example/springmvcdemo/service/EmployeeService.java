package org.example.springmvcdemo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.springmvcdemo.dao.EmployeeDao;
import org.example.springmvcdemo.ds.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeDao dao;

    public Employee findEmployeeById(int id) {
        return dao.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Employee> findAllEmployees() {
        return dao.findAll();
    }

    public void deleteEmployeeById(int id) {
        dao.deleteById(id);
    }

    public void createEmployee(Employee employee) {
        dao.save(employee);
    }

    public void updateEmployee(Employee employee, int id) {
        Employee oldEmployee = dao.findEmployeeById(id);
        oldEmployee.setId(employee.getId());
        oldEmployee.setFirstName(employee.getFirstName());
        oldEmployee.setLastName(employee.getLastName());
        oldEmployee.setEmail(employee.getEmail());
        oldEmployee.setDateOFBirth(employee.getDateOFBirth());
    }
}
