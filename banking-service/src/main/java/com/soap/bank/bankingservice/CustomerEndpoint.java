package com.soap.bank.bankingservice;

import bank.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
@Endpoint
public class CustomerEndpoint {

        private static final String NAMESPACE_URI = "bank";

        private BankRepository BankRepository;

        @Autowired
        public CustomerEndpoint(BankRepository BankRepository) {
            this.BankRepository = BankRepository;
        }

        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CustomerDetailsRequest")
        @ResponsePayload
        public CustomerDetailsResponse getCustomer(@RequestPayload CustomerDetailsRequest request) {
            CustomerDetailsResponse response = new CustomerDetailsResponse();
            response.setCustomer(BankRepository.getCustomer(request.getFirstName()));

            return response;
        }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BalanceRequest")
    @ResponsePayload
    public BalanceResponse getBalance(@RequestPayload BalanceRequest request) {
        BalanceResponse response = new BalanceResponse();
        response.setBalance(BankRepository.getBalance(request.getFirstName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DepositRequest")
    @ResponsePayload
    public DepositResponse deposit(@RequestPayload DepositRequest request) {
        DepositResponse response = new DepositResponse();
        response.setBalance(BankRepository.depositMoney(request.getFirstName(), request.getAmount()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "WithdrawRequest")
    @ResponsePayload
    public WithdrawResponse withdraw(@RequestPayload WithdrawRequest request) {
        WithdrawResponse response = new WithdrawResponse();
        response.setBalance(BankRepository.withdrawMoney(request.getFirstName(), request.getAmount()));
        return response;
    }





}
