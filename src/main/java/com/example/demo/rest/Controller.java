package com.example.demo.rest;


import com.example.demo.StudentNotFoundException;
import com.example.demo.entity.Student;
import com.example.demo.entity.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/all")
    List<Student> allStudent() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("ahmed", "rashed");
        Student student2 = new Student("mohamed", "rashed");
        Student student3 = new Student("yousef", "rashed");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return list;
    }

    @GetMapping("/one/{id}")
    Student oneStudent(@PathVariable int id) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("ahmed", "rashed"));
        list.add(new Student("mohamed", "rashed"));
        list.add(new Student("yousef", "rashed"));
        if (id > list.size()) {
            throw new StudentNotFoundException("id " + id + " is not found");
        }
        return list.get(id);
    }


  }
