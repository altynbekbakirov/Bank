package com.example;

import com.example.entity.Account;
import com.example.model.Role;
import com.example.repository.AccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        AccountRepository repository = context.getBean(AccountRepository.class);

        Account account = new Account("mike", "$2a$12$2a94rLh1AaHucZtpdXYRTuoJXAh8Z4lvROv/uJbura/ynyrwhSSiO", Role.ADMIN, 8D);
        repository.save(account);
    }


}
