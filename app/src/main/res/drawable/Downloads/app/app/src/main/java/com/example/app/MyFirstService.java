package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
    private final MyFirstClass myFirstClass;

    //@Autowired : we no longer need the autowired keyword because spring it will to inject all anything that is injectable. it is not recommended.It is recommende to use Constructor  injection or setter injection
    public MyFirstService(
            @Qualifier("bean1") MyFirstClass myFirstClass
    ) {
        this.myFirstClass = myFirstClass;
    }


    public String tellAStory() {
        return "The dependency is saying :" + myFirstClass.sayHello();
    }
}
