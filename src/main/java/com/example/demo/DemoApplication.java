package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.repo.StudentRepoImpl;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo) {
        return runner -> {
            //  createUser(repo);
            // findUser(repo, 10);
            //  getAllUsers(repo);
//            Student student = repo.find(2);
//            student.setFirstName("said");
//            updateStudent(repo, student);
            removeUser(repo,2);

        };
    }


    void removeUser(StudentRepo repo, int id) {
        repo.remove(id);
    }

    void updateStudent(StudentRepo repo, Student student) {
        repo.update(student);
    }

    void getAllUsers(StudentRepo repo) {
        var result = repo.getAll();
        result.forEach(System.out::println);
    }

    void findUser(StudentRepo repo, int id) {
        Student student = repo.find(id);
        System.out.println(student);
    }

    void createUser(StudentRepo repo) {
        System.out.println("hello world");
        Student student = new Student("ahmed", "rashed", "ahmed@gemail.com");
        System.out.println(student.getId() + "");
        repo.save(student);
        System.out.println(student.getId() + "");

    }

}
