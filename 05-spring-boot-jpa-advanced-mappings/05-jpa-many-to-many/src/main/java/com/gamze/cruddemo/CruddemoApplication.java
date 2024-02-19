package com.gamze.cruddemo;

import com.gamze.cruddemo.dao.AppDAO;
import com.gamze.cruddemo.entity.*;
import org.hibernate.usertype.CompositeUserType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting student id: "+theId);
		appDAO.deleteStudentById(theId);
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId=2;
		Student tempStudent=appDAO.findStudentAndCoursesByStudentId(theId);
		Course tempCourse1=new Course("Rubik's Cube");
		Course tempCourse2=new Course("Atari 2600");
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);
		System.out.println("Updating student: "+tempStudent);
		System.out.println("Associated courses: "+tempStudent.getCourses());
		appDAO.update(tempStudent);
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId=1;
		Student tempStudent=appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded student: "+tempStudent);
		System.out.println("Loaded courses: "+tempStudent.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId=1;
		Course tempCourse=appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: "+tempCourse);
		System.out.println("Loaded students: "+tempCourse.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course tempCourse=new Course("Painting ");
		Student tempStudent1=new Student("John","Doe","john@gmail.com");
		Student tempStudent2=new Student("Mary","Public","mary@gmail.com");
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		System.out.println("Saving the course: "+tempCourse);
		System.out.println("Saving the students: "+tempCourse.getStudents());
		appDAO.save(tempCourse);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting course id: "+theId);
		appDAO.deleteCourseById(theId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId=1;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println("tempCourse: "+tempCourse);
		System.out.println("the associated reviews: "+tempCourse.getReviews());
	}

	private void createCorseAndReviews(AppDAO appDAO) {
		Course tempCourse=new Course("Pacman - How to score one million points");
		tempCourse.addReview(new Review("Great course...loved it"));
		tempCourse.addReview(new Review("Cool course"));
		tempCourse.addReview(new Review("What a course"));
		appDAO.save(tempCourse);
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting course id: "+theId);

		appDAO.deleteCourseById(theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding course id: "+theId);
		Course tempCourse=appDAO.findCourseById(theId);
		System.out.println("tempCourse: "+tempCourse);
		tempCourse.setTitle("Enjoy the Simple Things");
		appDAO.update(tempCourse);
		System.out.println("tempCourse: "+tempCourse);
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		tempInstructor.setFirstName("Susan");
		appDAO.update(tempInstructor);
		System.out.println("tempInstructor: "+tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		List<Course> courses=appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor=new Instructor("Gamze","Sakallı","gamze@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.gamze.com/youtube","painting");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		Course tempCourse1=new Course("Air-Guitar");
		Course tempCourse2=new Course("The Pinball");
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		System.out.println("Saving the instructor: "+tempInstructor);
		System.out.println("The courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=3;
		System.out.println("Deleting instructor detail id: "+theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Deleted instructor detail");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId=3;
		System.out.println("Finding instructor detail id: "+theId);
		InstructorDetail tempInstructorDetail=appDAO.findInstructorDetailById(theId);
		System.out.println("Temp Instructor Detail: "+tempInstructorDetail);
		System.out.println("The associated instructor only: "+tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=1;
		System.out.println("Deleting instructor id: "+theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Deleted instructor");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=2;
		System.out.println("Finding instructor id: "+theId);
		Instructor tempInstructor=appDAO.findInstructorById(theId);
		System.out.println("Temp Instructor: "+tempInstructor);
		System.out.println("The associated instructorDetail only: "+tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {
        Instructor tempInstructor=new Instructor("Chad","Darby","darby@luv2code.com");
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","swimming");
		//Instructor tempInstructor=new Instructor("Madhu","Patel","madhu@luv2code.com");
		//InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.madhu.com/youtube","guitar");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		System.out.println("Saving the instructor: "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}
}
