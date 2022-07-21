package com.kcbgroup.main.controller;


import com.kcbgroup.main.domain.CustomerCreationDomain;
import com.kcbgroup.main.service.CustomerCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
public class CustomerCreationController {
    private Logger logger = LoggerFactory.getLogger(CustomerCreationController.class);
    private final CustomerCreationService customerCreationService;

    public CustomerCreationController(CustomerCreationService customerCreationService) {this.customerCreationService = customerCreationService;}

    @PostMapping("/ken_20970_customers")
    public CustomerCreationDomain createCustomer(@RequestBody CustomerCreationDomain customerCreationDomain){
        logger.debug("Received customer : {}",customerCreationDomain);

        return customerCreationService.save(customerCreationDomain);
    }

    @PutMapping("/ken20970_customers/{id}")
    public CustomerCreationService updateCustomer(@RequestBody CustomerCreationDomain customerCreationDomain, @PathVariable Long id){
        logger.debug("Requested to update customer : {}, with id : {}",customerCreationDomain,id);

        return customerCreationService;
    }


    @GetMapping("/customers/{id}")
    public Optional<CustomerCreationDomain> findOne(@PathVariable Integer id){
        logger.debug("REST request to get customers with id : {}", id);
        return customerCreationService.findById(id);
    }

    @GetMapping("/customers")
    public List<CustomerCreationDomain> findAll(){
        logger.debug("REST request to get all customers");
        return customerCreationService.findAll();
    }


}
