package com.odedoyinakindele.customerdatabaseapi.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewCustomerRequest {
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull
    private String email;

    public BillingDetailRequest billingDetailRequest;

    public NewCustomerRequest(String firstName, String lastName, String email, BillingDetailRequest billingDetailRequest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingDetailRequest = billingDetailRequest;
    }

    public NewCustomerRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BillingDetailRequest getBillingDetailRequest() {
        return billingDetailRequest;
    }

    public void setBillingDetailRequest(BillingDetailRequest billingDetailRequest) {
        this.billingDetailRequest = billingDetailRequest;
    }
}
