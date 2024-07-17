package com.springrest.shoppingportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.springrest.shoppingportal.services", "com.springrest.shoppingportal.Controller"})
public class ShoppingportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingportalApplication.class, args);
	}

}
//(scanBasePackages = "com.springrest.shoppingportal")