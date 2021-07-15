package com.darshan.springdatajpatutorial.repository;

import com.darshan.springdatajpatutorial.entity.Course;
import com.darshan.springdatajpatutorial.entity.Student;
import com.darshan.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Sudha")
                .lastName("Murthy")
                .build();
        Course course = Course.builder()
                .title("Python")
                .credit(6)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);

        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);

        List<Course> courseList = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("courseList = " + courseList);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllWithSorting() {
        Pageable sortCourseByTitle = PageRequest.of(
                0,
                3,
                Sort.by("title"));
        Pageable sortCourseByCreditDesc = PageRequest.of(
                0,
                3,
                Sort.by("credit").descending());
        Pageable sortCourseByTitleAndCreditDescending = PageRequest.of(
                0,
                5,
                Sort.by("title").descending()
                .and(Sort.by("credit"))
        );
        List<Course> courseList = courseRepository.findAll(sortCourseByTitleAndCreditDescending).getContent();
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageWithTenRecords = PageRequest.of(0,10);
        List<Course> courseList = courseRepository.findByTitleContaining("D",  firstPageWithTenRecords).getContent();
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Subhash")
                .lastName("Aggarwal")
                .build();
        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Sen")
                .emailId("abhi@gmail.com")
                .build();
        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudents(student);
        courseRepository.save(course);
    }
}