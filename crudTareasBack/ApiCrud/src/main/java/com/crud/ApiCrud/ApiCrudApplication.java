package com.crud.ApiCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.crud.ApiCrud.controller.TutorialController;


@SpringBootApplication
public class ApiCrudApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ApiCrudApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(TutorialController.class);
	}
}