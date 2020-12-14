package com.maescaprey.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application entry of the service.
 * 
 * @author Maes-Caprey
 */
@SpringBootApplication
public class Application {

	/**
	 * Main entry for this service.
	 * 
	 * @param arguments Optional application arguments
	 * @throws Exception
	 */
	public static void main(final String[] arguments) throws Exception {

		SpringApplication.run(Application.class, arguments);
	}
}
