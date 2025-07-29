package org.java.financespring;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceSpringApplication {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(FinanceSpringApplication.class, args);
    }

}
