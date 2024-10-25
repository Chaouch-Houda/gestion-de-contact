package com.example.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationConfig {
    @Bean("bean1")
    //instead of @Bean("bean1") we can write @Bean
    //                                       @Qualifier("bean1")
    //we cann write @Primary before the bean that we want to define it with higher priority , we delete the all @Qualifier here and in MyFirstService where we use it.
    public MyFirstClass myFirstBean() {
        return new MyFirstClass("First bean");
    }
    @Bean("bean2")
    public MyFirstClass mySecondBean() {
        return new MyFirstClass("Second bean");
    }
    @Bean("bean3")
    public MyFirstClass myThirdBean() {
        return new MyFirstClass("Third bean");
    }
}
