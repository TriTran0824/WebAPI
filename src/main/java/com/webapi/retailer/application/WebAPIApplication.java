package com.webapi.retailer.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * The main class that starts the web API spring boot application.
 */
@SpringBootApplication
@EntityScan(basePackages={"com.webapi.retailer.entity"})
@ComponentScan(basePackages={"com.webapi.retailer"})
public class WebAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAPIApplication.class, args);
	}

}
