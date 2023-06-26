package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyClass.class);
    
    public void myMethod() {
        logger.info("This is an example");
    }
    
    public void anotherMethod() {
        String message = "Hello, World!";
        logger.info(message);
    }
}

