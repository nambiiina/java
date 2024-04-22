package org.example.customerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.exceptions.CustomerNotFoundException;
import org.example.customerservice.exceptions.EmailAlreadyExistException;
import org.example.customerservice.mapper.CustomerMapper;
import org.example.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) throws EmailAlreadyExistException {
        log.info(String.format("Saving new customer => %s ", customerDTO.toString()));
        Optional<Customer> existCustomer = customerRepository.findByEmail(customerDTO.getEmail());
        if (existCustomer.isPresent()) {
            log.error(String.format("This email %s already exist ", customerDTO.getEmail()));
            throw new EmailAlreadyExistException();
        }
        Customer customerToSave = customerMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.fromCustomer(savedCustomer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.fromListCustomers(customers);
    }

    @Override
    public CustomerDTO findById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return customerMapper.fromCustomer(customer.get());
    }

    @Override
    public List<CustomerDTO> search(String keyword) {
        List<Customer> customers = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        return customerMapper.fromListCustomers(customers);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        customerDTO.setId(id);
        Customer customerToUpdate = customerMapper.fromCustomerDTO(customerDTO);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.fromCustomer(updatedCustomer);
    }

    @Override
    public void delete(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        customerRepository.deleteById(id);
    }
}
