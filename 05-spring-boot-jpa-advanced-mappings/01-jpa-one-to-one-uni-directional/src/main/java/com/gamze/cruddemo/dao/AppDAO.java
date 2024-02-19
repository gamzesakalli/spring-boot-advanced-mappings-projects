package com.gamze.cruddemo.dao;

import com.gamze.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void  deleteInstructorById(int theId);
}
