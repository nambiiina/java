package org.example.customerservice.controller;

import jakarta.validation.Valid;
import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/search")
    public List<CustomerDTO> search(@RequestParam String keyword) {
        return customerService.search(keyword);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO save(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
