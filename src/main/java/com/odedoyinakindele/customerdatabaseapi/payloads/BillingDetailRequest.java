package com.odedoyinakindele.customerdatabaseapi.payloads;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class BillingDetailRequest {
    @Length(min = 13, max = 13)
    private String accountNumber;
    private Tariff tariff;

    public BillingDetailRequest(String accountNumber, Tariff tariff) {
        this.accountNumber = accountNumber;
        this.tariff = tariff;
    }

    public BillingDetailRequest() {
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
        BillingDetailRequest that = (BillingDetailRequest) o;
        return Objects.equals(accountNumber, that.accountNumber) && tariff == that.tariff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, tariff);
    }
}
