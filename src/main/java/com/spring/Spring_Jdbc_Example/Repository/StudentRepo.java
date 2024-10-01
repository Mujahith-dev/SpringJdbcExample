package com.spring.Spring_Jdbc_Example.Repository;

import com.spring.Spring_Jdbc_Example.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate temp;

    public JdbcTemplate getTemp() {
        return temp;
    }
    @Autowired
    public void setTemp(JdbcTemplate temp) {
        this.temp = temp;
    }

    public void addStudent(Student s) {
        String sql = "insert into student (rollNo, name, age, city, marks) values (?,?,?,?,?)";
        temp.update(sql, s.getRollNo(), s.getName(), s.getAge(), s.getCity(), s.getMarks());
        System.out.println("Student inserted successfully");
    }

    public List<Student> getAllStudent() {
        String sql = "select * from student";
        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollNo(rs.getInt("rollNo"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setCity(rs.getString("city"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };
        return temp.query(sql, mapper);
    }

    public void updateStudent(Student s) {
        String sql = "update student set name =?, age=?, city=?, marks=? where rollNo=?";
        int out = temp.update(sql, s.getName(),s.getAge(), s.getCity(),s.getMarks(), s.getRollNo());
        if(out>0){
            System.out.println("Student updated successfully");
        }
        else {
            System.out.println("No student found with given RollNo");
        }

    }

    public boolean deleteStudentById(int s) {
        String sql = "Delete from student where rollNo=?";
        int out = temp.update(sql,s);
        if (out > 0) {
            System.out.println("Student deleted successfully");
            return true;
        } else {
            System.out.println("Student with RollNo " + s+ " not found");
            return false;
        }
    }

    public Student getStudentById(int rollNo) {
        String sql = "select * from student where rollno=?";
        RowMapper<Student> mapper= new RowMapper<Student>(){
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollNo(rs.getInt("rollNO"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setCity(rs.getString("city"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };
        return temp.queryForObject(sql,mapper, rollNo);
    }

    public List<Student> getStudentByName (String name){
        String sql = "Select * from student where name=?";
        RowMapper<Student> mapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student s = new Student();
                s.setRollNo(rs.getInt("rollNO"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setCity(rs.getString("city"));
                s.setMarks(rs.getInt("marks"));
                return s;
            }
        };
        return temp.query(sql, mapper, name);
    }
}
