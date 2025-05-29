package com.example.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(LoginApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
