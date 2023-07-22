package com.example.bookstoreproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BookStoreProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreProjectApplication.class, args);
		System.out.println("Your Application is Started...");
	    log.info("You are running Your Application");
	}


}
