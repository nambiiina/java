package org.example.customerservice.service;

import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.exceptions.CustomerNotFoundException;
import org.example.customerservice.exceptions.EmailAlreadyExistException;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerDTO customerDTO) throws EmailAlreadyExistException;
    List<CustomerDTO> findAll();
    CustomerDTO findById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> search(String keyword);
    CustomerDTO update(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException;
    void delete(Long id) throws CustomerNotFoundException;
}
