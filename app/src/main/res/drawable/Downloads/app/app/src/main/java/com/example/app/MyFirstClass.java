package com.example.app;

import org.springframework.stereotype.Component;

//@Component   : Supprimer la classe de configuration ApplicationConfig :
//Si vous préférez utiliser l'annotation @Component pour déclarer vos beans, vous pouvez supprimer la classe ApplicationConfig et annoter directement MyFirstClass avec @Bean.

public class MyFirstClass {
    private String myVar;

    public MyFirstClass(String myVar) {
        this.myVar = myVar;
    }

    public String sayHello() {
        return "Hello from the MyFirstClass ==>  myVar = " + myVar;
    }
}
