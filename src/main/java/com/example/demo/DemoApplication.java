package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MyClass {
    private static final String str = "Hello";
    
    public void myMethod() {
        System.out.println("This is an example");
    }
    
    public void AnotherMethod() {
        String message = "Hello, World!";
        System.out.println(message);
    }
}
