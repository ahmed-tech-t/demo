package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    EmployeeService service;

    @Autowired
    EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    List<Employee> getAllEmployee() {
        return service.getAll();
    }

    @GetMapping("/employees/{id}")
    Employee getById(@PathVariable int id) {
        Employee e = service.getById(id);
        if (e == null) throw new RuntimeException("invalid id");
        return e;
    }

    @PostMapping("/employees")
    Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        return service.saveEmployee(employee);
    }

    @PutMapping("/employees")
    Employee updateEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    void deleteById(@PathVariable int id) {
        Employee e = service.getById(id);
        if (e == null) throw new RuntimeException("invalid id");
        service.deleteEmployee(id);
    }
}
