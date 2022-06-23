package com.example.service;

import com.example.entity.Account;
import com.example.entity.Operation;
import com.example.model.OperationTypes;
import com.example.repository.AccountRepository;
import com.example.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Account getAccount() {
        return accountRepository.findAll().get(0);
    }

    @Transactional
    public Operation payOperation() {
        Account account = accountRepository.findAll().get(0);
        Operation operation = new Operation();
        operation.setCount(1.1);
        account.setBalance(account.getBalance() - 1.1);
        operation.setTypes(OperationTypes.PAYMENT);
        operation.setAccount(account);
        return operationRepository.save(operation);
    }

}
