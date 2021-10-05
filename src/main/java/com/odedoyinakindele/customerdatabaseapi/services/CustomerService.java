package com.odedoyinakindele.customerdatabaseapi.services;

import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;

import java.util.List;

public interface CustomerService {
    Customer save(NewCustomerRequest newCustomerRequest);
    Customer get(long id);
    List<Customer> get();
}
