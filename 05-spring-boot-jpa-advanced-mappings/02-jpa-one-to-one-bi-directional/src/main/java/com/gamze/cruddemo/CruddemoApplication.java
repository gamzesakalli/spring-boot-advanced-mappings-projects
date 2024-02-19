package com.gamze.cruddemo;

import com.gamze.cruddemo.dao.AppDAO;
import com.gamze.cruddemo.entity.Instructor;
import com.gamze.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);


		};
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
