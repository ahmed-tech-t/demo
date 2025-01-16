package com.example.demo.service;

import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(int id);

    void deleteEmployee(int id);

    Employee saveEmployee(Employee employee);


}
