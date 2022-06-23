package com.example.entity;

import com.example.model.OperationTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OperationTypes types;
    private Double count;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Operation() {
    }

    public Operation(OperationTypes types, Double count, Account account) {
        this.types = types;
        this.count = count;
        this.account = account;
    }

}
