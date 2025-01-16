package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepo repo;

    EmployeeServiceImpl(EmployeeRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee getById(int id) {
        Optional<Employee> result = repo.findById(id);
        Employee employee = null;
        if (result.isPresent()) employee = result.get();
        else throw new RuntimeException("invalid id");
        return employee;
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

}
