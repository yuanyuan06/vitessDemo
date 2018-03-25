package io;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration
public class MainApp {
    public static void main( String[] args ) throws IOException {
        SpringApplication.run(MainApp.class, args);
    }
}
