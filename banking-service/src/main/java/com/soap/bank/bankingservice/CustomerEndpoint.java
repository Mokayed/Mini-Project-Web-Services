package com.soap.bank.bankingservice;

import bank.CustomerDetailsRequest;
import bank.CustomerDetailsResponse;
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

}
