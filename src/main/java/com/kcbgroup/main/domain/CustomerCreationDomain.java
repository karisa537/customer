package com.kcbgroup.main.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ken_20970_customers")
public class CustomerCreationDomain {
    private String firstName;

    private String surName;

    private String middleName;
    private  String gender;

    private String identificationDocument;
    @Id
   // @NotNull
    private Integer idPassportNumber;

    private String address;

    private  String phoneNo;

    private String email;

    private String city;

    private String state;

    private String postalCode;

    //private Integer customerId;

    public CustomerCreationDomain() {
    }

    public CustomerCreationDomain(String firstname, String surName, String middleName, String gender, String identificationDocument, Integer idPassportNumber, String address, String phoneNo, String email, String city, String state, String postalCode /*Integer customerId*/) {
        this.firstName = firstname;
        this.surName = surName;
        this.middleName = middleName;
        this.gender = gender;
        this.identificationDocument = identificationDocument;
        this.idPassportNumber = idPassportNumber;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        //this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    public Integer getIdPassportNumber() {
        return idPassportNumber;
    }

    public void setIdPassportNumber(Integer idPassportNumber) {
        this.idPassportNumber = idPassportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

   /* public Integer getCustomerId(){
        return customerId;
    }

 ", customerId='" + customerId + '\'' +

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }*/

    @Override
    public String toString() {
        return "CustomerCreationDomain{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", identificationDocument='" + identificationDocument + '\'' +
                ", idPassportNumber=" + idPassportNumber +
                ", address='" + address + '\'' +
                ", phoneNo=" + phoneNo +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
