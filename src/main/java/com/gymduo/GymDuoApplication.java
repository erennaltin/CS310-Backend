package com.gymduo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class GymDuoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(GymDuoApplication.class, args);
	}

}
