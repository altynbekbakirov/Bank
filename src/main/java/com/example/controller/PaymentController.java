package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Operation;
import com.example.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final OperationService service;

    @Autowired
    public PaymentController(OperationService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> payment() {
        try {
            Account account = service.getAccount();
            if (account.getBalance() < 1.1) return ResponseEntity.badRequest().body("You have not enough balance");
            return ResponseEntity.ok(service.payOperation());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred");
        }
    }

    @GetMapping()
    public List<Operation> getAllOperations() {
        return service.getAllOperations();
    }

}
