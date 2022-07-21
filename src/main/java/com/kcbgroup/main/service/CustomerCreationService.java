package com.kcbgroup.main.service;

import com.kcbgroup.main.domain.CustomerCreationDomain;

import com.kcbgroup.main.utils.CreateCustomerFormatter;
import com.kcbgroup.main.repository.CustomerCreationRepository;
import org.apache.commons.lang3.StringUtils;
import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kcbgroup.main.utils.CustomProperties;
import com.kcbgroup.main.utils.HttpClient;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;



@Service
@CrossOrigin(origins="*")
//@RequestMapping("/api/v1/")
public class CustomerCreationService {


    @Autowired
    private HttpClient httpClient;

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private CreateCustomerFormatter createCustomerFormatter;

    private final  Logger logger = LoggerFactory.getLogger(CustomerCreationDomain.class);

    private final CustomerCreationRepository customerCreationRepository;

    public CustomerCreationService(CustomerCreationRepository customerCreationRepository){this.customerCreationRepository = customerCreationRepository;}
    public CustomerCreationDomain save(CustomerCreationDomain customerCreationDomain) {
        logger.info("Requested to create customer :{}", customerCreationDomain);

        try {
            customerCreationDomain = customerCreationRepository.save(customerCreationDomain);
            HashMap<String, String> xml = createCustomerFormatter.formatCustomerCreateRequest(customerCreationDomain);
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
                    //customerCreationDomain.setCustomerId(Integer.valueOf(transactionId));

                    //create request for A/C create
                    //invoke t24 again
                    //exit

                    customerCreationDomain = customerCreationRepository.save(customerCreationDomain);
                    return customerCreationRepository.save(customerCreationDomain);


                }

            }
        }
        catch(Exception e) {
            logger.error(e.getMessage());
            return genericReturnMessage(customerCreationDomain, "500", e.getMessage(), null);
        }






         return customerCreationDomain;
    }

    private CustomerCreationDomain genericReturnMessage(CustomerCreationDomain customerCreationDomain, String responseMessage, String responseCode, String responseBody) {
        return customerCreationDomain;


    }

    public Optional<CustomerCreationDomain> findById(Integer id){
        logger.info("Requested to find customer with id : {}", id);
        return customerCreationRepository.findById(id);
    }

    public List<CustomerCreationDomain> findAll(){
        logger.info("Request to find all customers");
        return customerCreationRepository.findAll();
    }




}
