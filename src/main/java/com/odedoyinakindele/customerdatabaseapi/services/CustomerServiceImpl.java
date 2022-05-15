package com.odedoyinakindele.customerdatabaseapi.services;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import com.odedoyinakindele.customerdatabaseapi.models.BillingDetail;
import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.repositories.BillingDetailRepository;
import com.odedoyinakindele.customerdatabaseapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BillingDetailRepository billingDetailRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, BillingDetailRepository billingDetailRepository) {
        this.customerRepository = customerRepository;
        this.billingDetailRepository = billingDetailRepository;
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
        if(customerRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("try another email");
        }
        String accountNumber = request.getBillingDetailRequest().getAccountNumber();
        if(accountNumber.length() != 13 || !accountNumber.endsWith("-01")){
            throw new IllegalArgumentException("incorrect account number");
        }

        if(billingDetailRepository.findByAccountNumber(accountNumber).isPresent()){
            throw new IllegalArgumentException("account number already taken");
        }

        Tariff tariff = request.getBillingDetailRequest().getTariff();
        BillingDetail billingDetail = new BillingDetail(accountNumber, tariff);

        return new Customer(firstName, lastName, email, billingDetail);
    }
}
