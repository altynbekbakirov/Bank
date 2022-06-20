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

    public List<Operation> getOperationsByAccountId(Long id) {
        return operationRepository.getAllByAccountId(id);
    }

    public Operation addOperation(Operation operation, Long id) {
        Account account = accountRepository.getReferenceById(id);
        operation.setAccount(account);
        return operationRepository.save(operation);
    }

    @Transactional
    public Operation payOperation(Long id) {
        Account account = accountRepository.getReferenceById(id);
        Operation operation = new Operation();
        operation.setCount(1.1);
        operation.setTypes(OperationTypes.PAYMENT);
        operation.setAccount(account);
        return operationRepository.save(operation);
    }

}
