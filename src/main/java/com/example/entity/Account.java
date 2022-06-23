package com.example.entity;

import com.example.model.Role;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private Role role;
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations;

    public Account() {
    }

    public Account(String username, String password, Role role, Double balance) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }
}
