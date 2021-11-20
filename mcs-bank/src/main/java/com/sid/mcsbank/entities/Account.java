package com.sid.mcsbank.entities;

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
    private Long id;
    private double balance;
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;
    @Enumerated(EnumType.STRING) //numeric type
    private AccountType accountType;
}
