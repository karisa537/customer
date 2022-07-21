package com.kcbgroup.main.controller;

import com.kcbgroup.main.domain.AccountCreationDomain;
import com.kcbgroup.main.service.AccountCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AccountCreationController {
    private Logger logger = LoggerFactory.getLogger(CustomerCreationController.class);
    private final AccountCreationService accountCreationService;

        public AccountCreationController(AccountCreationService accountCreationService) {
        this.accountCreationService = accountCreationService;
    }
    @PostMapping("/ken20970_Accounts")
    public AccountCreationDomain createAccount(@RequestBody AccountCreationDomain accountCreationDomain){
        logger.debug("Received customer : {}",accountCreationDomain);

        return accountCreationService.save(accountCreationDomain);
    }


}
