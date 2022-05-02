package com.example.billingservice.controller;

import com.example.billingservice.entities.Billing;
import com.example.billingservice.repository.BillingRepository;
import com.example.billingservice.repository.ProductItemRepository;
import com.example.billingservice.service.feign.CustomerService;
import com.example.billingservice.service.feign.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {

    private BillingRepository billingRepository;

    private ProductItemRepository productItemRepository;

    private ProductService productService;

    private CustomerService customerService;

    public BillingController(BillingRepository billingRepository, ProductItemRepository productItemRepository, ProductService productService, CustomerService customerService) {
        this.billingRepository = billingRepository;
        this.productItemRepository = productItemRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/fullBilling/{id}")
    public Billing getBilling(@PathVariable(name = "id") Long id) {
        Billing billing = billingRepository.findById(id).get();
        billing.setCustomer(customerService.findCustomerById(billing.getCustomerID()));
        billing.getProductItems().forEach(productItem -> {
            productItem.setProduct(productService.findProductById(productItem.getProductID()));
        });
        return billing;
    }
}
