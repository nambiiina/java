package com.example.springrest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private double balance;
    private Date creationDate;
    @Enumerated(EnumType.STRING) // => default ORDINAL (numeric)
    private AccountType type;
}
