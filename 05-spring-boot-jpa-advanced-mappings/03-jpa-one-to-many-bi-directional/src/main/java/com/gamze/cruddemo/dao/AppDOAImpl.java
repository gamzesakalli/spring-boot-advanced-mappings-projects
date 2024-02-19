package com.gamze.cruddemo.dao;

import com.gamze.cruddemo.entity.Course;
import com.gamze.cruddemo.entity.Instructor;
import com.gamze.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDOAImpl implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager  using constructor injection

    @Autowired
    public AppDOAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
    entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor=entityManager.find(Instructor.class, theId);
        List<Course> courses=tempInstructor.getCourses();
        for(Course tempCourse:courses){
            tempCourse.setInstructor(null);
        }
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class, theId);
        //remove the associated object reference
        //break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id= :data", Course.class
        );
        query.setParameter("data",theId);
        List<Course>courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "+
                "JOIN FETCH i.courses "+
                "JOIN FETCH i.instructorDetail "        +
                        "where i.id= :data", Instructor.class);
        query.setParameter("data",theId);
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse=entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }
}
