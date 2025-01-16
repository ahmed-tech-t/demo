package com.example.demo.repo;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
// name of end point
@RepositoryRestResource(path = "employees")
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
