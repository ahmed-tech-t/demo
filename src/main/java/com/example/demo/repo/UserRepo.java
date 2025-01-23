package com.example.demo.repo;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;



@Repository
@RepositoryRestResource(path = "employees")
public interface UserRepo extends JpaRepository<Users, UUID> {
    Users getUserByUsername(String username);
}
