package com.odedoyinakindele.customerdatabaseapi.services;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import com.odedoyinakindele.customerdatabaseapi.models.BillingDetail;
import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(NewCustomerRequest newCustomerRequest) {
        Customer customer = getCustomer(newCustomerRequest);

        return customerRepository.save(customer);
    }

    @Override
    public Customer get(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @Override
    public List<Customer> get() {
        return customerRepository.findAll();
    }

    private Customer getCustomer(NewCustomerRequest request){
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String email = request.getEmail();
        String accountNumber = request.getBillingDetailRequest().getAccountNumber();
        if(accountNumber.length() != 13 || accountNumber.charAt(10) != '-'){
            throw new IllegalArgumentException("incorrect account number");
        }
        Tariff tariff = request.getBillingDetailRequest().getTariff();
        BillingDetail billingDetail = new BillingDetail(accountNumber, tariff);

        return new Customer(firstName, lastName, email, billingDetail);
    }
}
