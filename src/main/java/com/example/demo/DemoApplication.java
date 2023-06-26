package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        String cls = "texto"; 
        SpringApplication.run(DemoApplication.class, args);
        log.info("A");
    }   
    
    public void unnecessaryMethod() {
        int x = 10;
        int y = 20;
        int sum = x + y;
        System.out.println("Sum: " + sum);
    }
    
    public void emptyMethod() {
        
    }
    
    public void invalidMethodName() {
        int _abc = 10;
        System.out.println(_abc);
    }
    
    public void unnecessaryComment() {
        // This is an unnecessary comment
        System.out.println("Hello");
    }
}
