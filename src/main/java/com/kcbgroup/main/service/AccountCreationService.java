package com.kcbgroup.main.service;

import com.kcbgroup.main.domain.AccountCreationDomain;

import com.kcbgroup.main.domain.CustomerCreationDomain;
import com.kcbgroup.main.repository.AccountCreationRepository;
import com.kcbgroup.main.utils.CreateAccountFormatter;
import com.kcbgroup.main.utils.CustomProperties;
import com.kcbgroup.main.utils.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@CrossOrigin("*")
public class AccountCreationService {

  @Autowired
    private HttpClient httpClient;

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private CreateAccountFormatter createAccountFormatter;

    private final  Logger logger = LoggerFactory.getLogger(AccountCreationDomain.class);

    @Autowired
    private final AccountCreationRepository accountCreationRepository;

    public AccountCreationService(AccountCreationRepository accountCreationRepository){this.accountCreationRepository = accountCreationRepository;}
    public AccountCreationDomain save(AccountCreationDomain accountCreationDomain) {
        logger.info("Requested to save customer :{}", accountCreationDomain);

        try {

            HashMap<String, String> xml = createAccountFormatter.formatAccountCreateRequest(accountCreationDomain);
            HashMap<String, String> response = httpClient.callT24(String.valueOf(xml));

            if (xml.get("RESPONSE_CODE")== "000"){
                HashMap<String, String> T24 = new HashMap<String, String>();
                T24 = httpClient.callT24(xml.get("RESPONSE_BODY"));
                if (T24.get("RESPONSE_CODE") == "000"){
                    String requestResponse = T24.get("RESPONSE_BODY");
                   String transactionId = StringUtils.substringBetween(requestResponse,"<transactionId>","<transactionId>");
                   String successId = StringUtils.substringBetween(requestResponse,"<successIndicator>","</successIndicator>");

                    logger.info("SuccessId {}",successId);
                    logger.info("TransactionId {}",transactionId);
                    accountCreationDomain.setAccountNumber(Integer.parseInt(transactionId));


                    accountCreationDomain = accountCreationRepository.save(accountCreationDomain);
                    return accountCreationRepository.save(accountCreationDomain);


                }

            }
        }
        catch(Exception e) {
            logger.error(e.getMessage());
            return genericReturnMessage(accountCreationDomain, "500", e.getMessage(), null);
        }

        return accountCreationDomain;
    }

    private AccountCreationDomain genericReturnMessage(AccountCreationDomain customerCreationDomain, String responseMessage, String responseCode, String responseBody) {
        return customerCreationDomain;


    }

    public Optional<AccountCreationDomain> findById(Integer id){
        logger.info("Requested to find customer with id : {}", id);
        return accountCreationRepository.findById(id);
    }

    public List<AccountCreationDomain> findAll(){
        logger.info("Request to find all customers");
        return accountCreationRepository.findAll();
    }

}