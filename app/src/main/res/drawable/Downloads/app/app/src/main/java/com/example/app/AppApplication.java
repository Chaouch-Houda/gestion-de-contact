package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {

		var ctx = SpringApplication.run(AppApplication.class, args);
/*
		MyFirstClass myFirstClass = ctx.getBean(MyFirstClass.class);
		System.out.println(myFirstClass.sayHello());
 */
		MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
		System.out.println(myFirstService.tellAStory());
	}

/*	@Bean
	public MyFirstClass myFirstClass() {
		return new MyFirstClass() ;
	}
 */
	// we use @Qualifier if we have multiple beans of the same class

}
