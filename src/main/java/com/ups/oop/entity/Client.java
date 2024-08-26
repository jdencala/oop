package com.ups.oop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client extends Person {
    private String clientCode;
    @OneToMany(mappedBy = "client")
    private List<Loan> loans = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(String clientCode, String personId, String name, String lastname, Integer age) {
        super(personId, name, lastname, age);
        this.clientCode = clientCode;
    }
}
