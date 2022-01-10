package com.example.springrest.dtos;

import lombok.Data;

@Data
public class RequestTransferDTO {
    private Long sourceCode;
    private Long destinationCode;
    private double amount;
}
