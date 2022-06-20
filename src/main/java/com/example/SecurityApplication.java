package com.example;

import com.example.entity.Account;
import com.example.entity.Operation;
import com.example.model.OperationTypes;
import com.example.model.Role;
import com.example.repository.AccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        AccountRepository repository = context.getBean(AccountRepository.class);

        Account account = new Account("mike", "$2a$12$2a94rLh1AaHucZtpdXYRTuoJXAh8Z4lvROv/uJbura/ynyrwhSSiO", Role.ADMIN);

        Operation operation = new Operation(OperationTypes.COLLECTION, 8.00, account);
        List<Operation> operations = Collections.singletonList(operation);

        account.setOperations(operations);
        repository.save(account);

        Account account1 = new Account("manny", "$2a$12$37bLDZLGVgqU3YjNtjqxL.vhJi2U.K5QZbt7WKCryz3YXJE3C/WX6", Role.ADMIN);

        Operation operation1 = new Operation(OperationTypes.COLLECTION, 8.00, account1);
        List<Operation> operations1 = Collections.singletonList(operation1);

        account1.setOperations(operations1);
        repository.save(account1);
    }


}
