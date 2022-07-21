package com.kcbgroup.main.utils;

import com.kcbgroup.main.domain.CustomerCreationDomain;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;



import freemarker.template.Configuration;
import freemarker.template.Template;

@Setter
@Getter
@Service
public class CreateCustomerFormatter {
    private Logger logger = LoggerFactory.getLogger(CreateCustomerFormatter.class);

    @Autowired
    private Configuration freemarker;

    @Autowired
    private CustomProperties customProperties;

    public HashMap<String, String> formatCustomerCreateRequest(CustomerCreationDomain customerCreationDomain) {
        HashMap<String, String> response = new HashMap<String, String>();
        Map<String, Object> templateData = new HashMap<String, Object>();
        try {
            freemarker.setClassForTemplateLoading(this.getClass(), "/templates");
            Template templates = freemarker.getTemplate("createCustomer.ftl");


            templateData.put("clientUsername", customProperties.getClientUsername());
            templateData.put("clientPassword", customProperties.getClientPassword());
            templateData.put("serviceName", customProperties.getServiceName());



            templateData.put("firstName", customerCreationDomain.getFirstName());
            templateData.put("middleName", customerCreationDomain.getMiddleName());
            templateData.put("surName", customerCreationDomain.getSurName());
            templateData.put("idPassportNumber", customerCreationDomain.getIdPassportNumber());
            templateData.put("identificationDocument", customerCreationDomain.getIdentificationDocument());
            templateData.put("address", customerCreationDomain.getAddress());
            templateData.put("city", customerCreationDomain.getCity());
            templateData.put("postalCode", customerCreationDomain.getPostalCode());
            templateData.put("phoneNo",customerCreationDomain.getPhoneNo());
            templateData.put("gender",customerCreationDomain.getGender());
            templateData.put("email",customerCreationDomain.getEmail());
            templateData.put("state",customerCreationDomain.getState());
            templateData.put("fullName",customerCreationDomain.getFirstName() + " " +customerCreationDomain.getSurName());


            String requestXml = FreeMarkerTemplateUtils.processTemplateIntoString(templates, templateData);

            logger.info("------------------------- T24 REQUEST -------------------------");
            String saveReq = "";
            saveReq = requestXml.replaceAll("(?s)<ClientUserName>.*?</ClientUserName>", "<ClientUserName>******</ClientUserName>");
            saveReq = saveReq.replaceAll("(?s)<ClientPassword>.*?</ClientPassword>", "<ClientPassword>******</ClientPassword>");
            logger.info(saveReq);

            response.put("RESPONSE_CODE", "000");
            response.put("RESPONSE_BODY", requestXml);

            return response;
        } catch (Exception e) {
            response.put("RESPONSE_CODE", "500");
            response.put("RESPONSE_BODY", e.getMessage());
            logger.error("----------- FAILED TO CREATE CUSTOMER-CREATE REQUEST | MISSING PARAMETERS --------");
            logger.error(e.getStackTrace().toString());
            logger.error(e.getMessage());
        }
        return response;
    }
}
