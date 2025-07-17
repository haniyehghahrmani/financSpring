package org.java.financespring;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.java.financespring.model.pgmodel.Permission;
import org.java.financespring.model.pgmodel.Role;
import org.java.financespring.model.pgmodel.User;
import org.java.financespring.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class FinanceSpringApplication {
    private static UserService userService;

    public FinanceSpringApplication(UserService userService) {
        FinanceSpringApplication.userService = userService;
    }


    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(FinanceSpringApplication.class, args);

        User user =
                User
                        .builder()
                        .role(
                                Role
                                        .builder()
                                        .roleName("Admin")
                                        .permissions(
                                                Set.of(
                                                        Permission.builder().name("SAVE_DATA").build(),
                                                        Permission.builder().name("EDIT_DATA").build()
                                                )
                                        )
                                        .build()
                        )
                        .username("Ahmad")
                        .password("Messbah1234")
                        .build();

        userService.save(user);
        System.out.println("Role Saved");
    }

}
