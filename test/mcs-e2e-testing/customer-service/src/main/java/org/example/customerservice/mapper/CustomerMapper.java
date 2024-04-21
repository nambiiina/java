package org.example.customerservice.mapper;

import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CustomerDTO fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> fromListCustomers(List<Customer> customers) {
        return customers.stream().map(customer -> fromCustomer(customer)).toList();
    }
}
