package com.kcbgroup.main.domain;

import javax.persistence.*;

import static javax.swing.plaf.synth.Region.TABLE;
import static org.hibernate.FetchMode.JOIN;


@Entity
@Table(name = "ken20970_Accounts")
public class AccountCreationDomain {


    @Id
    private Integer accountNumber;
    private Integer accountBalance;
    private String customerId;


    public AccountCreationDomain() {
    }

    public AccountCreationDomain(String customerId, Integer accountNumber, Integer accountBalance) {

        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.customerId = customerId;

    }

   public String getCustomerId(){
        return customerId;
   }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "AccountCreationDomain{" +
                "accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
