package com.odedoyinakindele.customerdatabaseapi.models;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 13, unique = true)
    private String accountNumber;
    private Tariff tariff;

    public BillingDetail() {
    }

    public BillingDetail(Long id, String accountNumber, Tariff tariff) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.tariff = tariff;
    }

    public BillingDetail(String accountNumber, Tariff tariff) {
        this.accountNumber = accountNumber;
        this.tariff = tariff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingDetail that = (BillingDetail) o;
        return Objects.equals(id, that.id) && Objects.equals(accountNumber, that.accountNumber) && tariff == that.tariff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, tariff);
    }
}
