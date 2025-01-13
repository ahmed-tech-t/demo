package com.example.demo.repo;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentRepo {
    void save(Student student);
    Student find(int id);
    List<Student> getAll();
    Student update(Student student);
    void remove(int id);
}
