package com.odedoyinakindele.customerdatabaseapi.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewCustomerRequest that = (NewCustomerRequest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(billingDetailRequest, that.billingDetailRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, billingDetailRequest);
    }
}
