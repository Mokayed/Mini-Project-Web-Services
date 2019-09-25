package com.soap.bank.bankingservice;

import bank.Account;
import bank.Customer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import bank.*;

@Component
public class BankRepository {
    protected static final Map<String, Customer> customers = new HashMap<>();

    @PostConstruct
    public void InitData() {
        Account acc = new Account();
        acc.setBalance(-500);
        acc.setId(001);

        Customer c = new Customer();
        c.setId(001);
        c.setFirstName("Morched");
        c.setLastName("Kayed");
        c.setAddress("Istedgade 86");
        c.setPhoneNumber("+4526331172");
        c.setAccount(acc);
        customers.put(c.getFirstName(), c);

         acc = new Account();
        acc.setBalance(500);
        acc.setId(002);

        c = new Customer();
        c.setId(002);
        c.setFirstName("Hallur");
        c.setLastName("ashdaskdas");
        c.setAddress("Istedgade 86");
        c.setPhoneNumber("+4526331172");
        c.setAccount(acc);
        customers.put(c.getFirstName(), c);

        acc = new Account();
        acc.setBalance(1500);
        acc.setId(003);

         c = new Customer();
        c.setId(003);
        c.setFirstName("Hazem");
        c.setLastName("Saeid");
        c.setAddress("Istedgade 86");
        c.setPhoneNumber("+4526331172");
        c.setAccount(acc);
        customers.put(c.getFirstName(), c);

    }

    public Customer getCustomer(String firstName) {
       // Assert.notNull(firstName, "firstname must not be null");
        Customer c = customers.get("Morched");

        return customers.get(firstName);
    }

    public long getBalance(String firstName) {
        Assert.notNull(firstName, "firstname must not be null");
        System.out.println(customers.get(firstName).getAccount().getBalance());
        return customers.get(firstName).getAccount().getBalance();

    }

    public String depositMoney(String firstName, long amount) {
        if (amount <= 0) return "You must deposit a positive value";
        Customer c = customers.get(firstName);
        c.getAccount().setBalance(c.getAccount().getBalance()+amount);
        customers.put(firstName, c);
        return "Succesfully deposited: " + amount + " to your balance, your balance is now: " + c.getAccount().getBalance();
    }

    public String withdrawMoney(String firstName, long amount) {
        if (amount <= 0) return "You must withdraw a positive amount";
        Customer c = customers.get(firstName);
        if(c.getAccount().getBalance() < amount) return "You do not have money enough, to withdraw: " + amount;
        c.getAccount().setBalance(c.getAccount().getBalance() - amount);
        customers.put(c.getFirstName(), c);
        return "Successfully withdrawed: " + amount + ". Balance is now: " + c.getAccount().getBalance();
    }
}