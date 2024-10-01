package com.spring.Spring_Jdbc_Example.Services;

import com.spring.Spring_Jdbc_Example.Model.Student;
import com.spring.Spring_Jdbc_Example.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServices {

    private StudentRepo repo;
    public StudentRepo getRepo() {
        return repo;
    }
    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    public void addStudent(Student s) {
        repo.addStudent(s);
    }

    public List<Student> getAllStudents() {
       return repo.getAllStudent();
    }

    public void updateStudent(Student s) {
        repo.updateStudent(s);
    }

    public boolean deleteStudentById(int s) {
        return repo.deleteStudentById(s);
    }

    public Student getStudentById(int rollNo) {
        return repo.getStudentById(rollNo);
    }

    public List<Student> getStudentByName(String name) {
       return repo.getStudentByName(name);
    }
}
