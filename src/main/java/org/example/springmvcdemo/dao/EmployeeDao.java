package org.example.springmvcdemo.dao;

import org.example.springmvcdemo.ds.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(int id);
}
