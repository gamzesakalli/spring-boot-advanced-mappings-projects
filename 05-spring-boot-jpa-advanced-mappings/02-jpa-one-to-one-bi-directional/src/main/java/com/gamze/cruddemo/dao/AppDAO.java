package com.gamze.cruddemo.dao;

import com.gamze.cruddemo.entity.Instructor;
import com.gamze.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void  deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void  deleteInstructorDetailById(int theId);
}
