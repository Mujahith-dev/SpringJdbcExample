package com.spring.Spring_Jdbc_Example;

import com.spring.Spring_Jdbc_Example.Model.Student;
import com.spring.Spring_Jdbc_Example.Services.StudentServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class SpringJdbcExampleApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringJdbcExampleApplication.class, args);
		Student s = context.getBean(Student.class);

//		s.setRollNo(104);
//		s.setName("Sai");
//		s.setAge(19);
//		s.setCity("Chn");
//		s.setMarks(75);

		StudentServices service = context.getBean(StudentServices.class);

        }

		//to add student
//		service.addStudent(s);

		//to get all students
//		List<Student> stud = service.getAllStudents();
//		System.out.println(stud);
//		stud.forEach(System.out::println);

		//to update student with primary key
//		s.setRollNo(104);
//		s.setName("Ram");
//		s.setCity("Chennai");
//		service.updateStudent(s);

		//to delete student with primary key
//		s.setRollNo(104);
//		service.deleteStudentById(s);

		//get student by rollNo
//		int rollNo = 104;
//		Student stud = service.getStudentById(rollNo);
//		System.out.println(stud);

		//get all students by the name
//		String name = "Mujahith";
//		List<Student> stud = service.getStudentByName(name);
//		stud.forEach(System.out::println);

}
