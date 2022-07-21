package com.kcbgroup.main.utils;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "t24")
public class CustomProperties {
    private String clientUsername;
    private String clientPassword;
    private String serviceName;
    private String baseUrl;
    private String baseResource;
   /* private int httpConnectionRequestTimeout;
    private int httpConnectionTimeout;
    private int httpConnectionReadTimeout;*/

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseResource() {
        return baseResource;
    }

    public void setBaseResource(String baseResource) {
        this.baseResource = baseResource;
    }

   /* public int getHttpConnectionRequestTimeout() {
        return httpConnectionRequestTimeout;
    }

    public void setHttpConnectionRequestTimeout(int httpConnectionRequestTimeout) {
        this.httpConnectionRequestTimeout = httpConnectionRequestTimeout;
    }

    public int getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    public void setHttpConnectionTimeout(int httpConnectionTimeout) {
        this.httpConnectionTimeout = httpConnectionTimeout;
    }

    public int getHttpConnectionReadTimeout() {
        return httpConnectionReadTimeout;
    }

    public void setHttpConnectionReadTimeout(int httpConnectionReadTimeout) {
        this.httpConnectionReadTimeout = httpConnectionReadTimeout;
    }*/
}
