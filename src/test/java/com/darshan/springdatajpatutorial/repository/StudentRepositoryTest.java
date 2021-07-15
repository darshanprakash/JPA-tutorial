package com.darshan.springdatajpatutorial.repository;

import com.darshan.springdatajpatutorial.entity.Guardian;
import com.darshan.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("darshanprakash.rvce@gmail.com")
                .firstName("Darshan")
                .lastName("Prakash")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Vasant")
                .email("vasant@gmail.com")
                .mobile("3828973483")
                .build();
        Student student = Student.builder()
                .emailId("rahul@gmail.com")
                .firstName("rahul")
                .lastName("kumar")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Darshan");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("sha");
        System.out.println(studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> studentList = studentRepository.findByGuardianName("Prakash");
        System.out.println(studentList);
    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        String studentFirstName = studentRepository.getStudentFirstNameByEmailAddress("darshanprakash.rvce@gmail.com");
        System.out.println(studentFirstName);
    }

    @Test
    public void printStudentByEmailAddress() {
        Student student = studentRepository.getStudentByEmailAddressNative("darshanprakash.rvce@gmail.com");
        System.out.println(student);
    }

    @Test
    public void printStudentByEmailAddressNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("darshanprakash.rvce@gmail.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Darsh", "darshanprakash.rvce@gmail.com");
    }
}