package com.example.cron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CronApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronApplication.class, args);
		System.out.println("***************service started*********");
	}
}
