package com.odedoyinakindele.customerdatabaseapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private BillingDetail billingDetail;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, BillingDetail billingDetail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingDetail = billingDetail;
    }

    public Customer(Long id, String firstName, String lastName, String email, BillingDetail billingDetail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingDetail = billingDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BillingDetail getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(BillingDetail billingDetail) {
        this.billingDetail = billingDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(email, customer.email) && Objects.equals(billingDetail, customer.billingDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, billingDetail);
    }
}
