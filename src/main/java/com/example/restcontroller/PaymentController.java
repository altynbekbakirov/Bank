package com.example.restcontroller;

import com.example.entity.Operation;
import com.example.model.OperationTypes;
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

    @PostMapping("/{accountId}")
    public ResponseEntity<?> payment(@PathVariable Long accountId) {
        try {
            List<Operation> operations = service.getOperationsByAccountId(accountId);
            double sum = 0;
            for (Operation operation : operations) {
                if (operation.getTypes() == OperationTypes.COLLECTION) {
                    sum += operation.getCount();
                } else {
                    sum -= operation.getCount();
                }
            }
            if (sum < 1.1) return ResponseEntity.badRequest().body("You have not enough balance");
            return ResponseEntity.ok(service.payOperation(accountId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred");
        }
    }

    @GetMapping("/{accountId}")
    public List<Operation> getAllOperations(@PathVariable Long accountId) {
        return service.getOperationsByAccountId(accountId);
    }

}
