package com.gamze.cruddemo.dao;

import com.gamze.cruddemo.entity.Course;
import com.gamze.cruddemo.entity.Instructor;
import com.gamze.cruddemo.entity.InstructorDetail;
import com.gamze.cruddemo.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    Course findCourseById(int theId);
    void  deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void  deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course course);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
    Course findCourseAndStudentsByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);
    void update(Student tempStudent);

    void deleteStudentById(int theId);
}
