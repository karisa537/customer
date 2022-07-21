package com.kcbgroup.main.utils;

import com.kcbgroup.main.domain.AccountCreationDomain;
import com.kcbgroup.main.service.CustomerCreationService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
public class CreateAccountFormatter {

    private Logger logger = LoggerFactory.getLogger(CreateAccountFormatter.class);

    @Autowired
    private Configuration freemarker;

    @Autowired
    private CustomProperties customProperties;

    public HashMap<String, String> formatAccountCreateRequest(AccountCreationDomain accountCreationDomain) {
        HashMap<String, String> response = new HashMap<String, String>();
        Map<String, Object> templateData = new HashMap<String, Object>();
        try {
            freemarker.setClassForTemplateLoading(this.getClass(), "/templates");



            templateData.put("clientUsername", customProperties.getClientUsername());
            templateData.put("clientPassword", customProperties.getClientPassword());
            templateData.put("serviceName", customProperties.getServiceName());


            Template templates = freemarker.getTemplate("createAccount.ftl");

            templateData.put("customerId", accountCreationDomain.getCustomerId());




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
            logger.error("-- FAILED TO CREATE CUSTOMER-CREATE REQUEST-----");
            logger.error(e.getStackTrace().toString());
            logger.error(e.getMessage());
        }
        return response;
    }
}
