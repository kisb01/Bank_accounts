package com.tradebrite.bank_accounts.controller;

import com.tradebrite.bank_accounts.converter.StringToLong;
import com.tradebrite.bank_accounts.model.Account;
import com.tradebrite.bank_accounts.model.Customer;
import com.tradebrite.bank_accounts.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            return customerService.findById(StringToLong.convert(id));
        }
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer customer, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new RuntimeException("Must enter a valid number");
        } else {
            customer.setId(StringToLong.convert(id));
        }
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        customerService.deleteById(StringToLong.convert(id));
    }
}
