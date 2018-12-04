package com.tech.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tech.entity.JobRepository;
import com.tech.entity.Student;
import com.tech.entity.StudentRepository;

@SpringBootApplication

@ComponentScan({"com.tech"})
@EntityScan("com.tech.entity")
@EnableJpaRepositories("com.tech.entity")

public class Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;
	
	@Autowired
	JobRepository jobRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		
		
		logger.info("Student id 10001 -> {}", repository.findById(10001L));

		/*logger.info("Inserting -> {}", repository.save(new Student("John", "A1234657")));
		logger.info("Inserting -> {}", repository.save(new Student("AA", "A1234657")));*/
		
		
		/*logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));
		
		logger.info("Inserting -> {}", repository.save(new Student("John", "A1234657")));

		logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));
		
		logger.info("Inserting -> {}", repository.save(new Student("John", "A1234657")));

		logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));
		
		
		logger.info("Inserting -> {}", repository.save(new Student("John", "A1234657")));

		logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));
		
		logger.info("Inserting -> {}", repository.save(new Student("John", "A1234657")));

		logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));*/

		//repository.deleteById(10002L);

		//logger.info("All users -> {}", repository.findAll());
	}
}
