package com.odedoyinakindele.customerdatabaseapi.payloads;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import org.hibernate.validator.constraints.Length;

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
}
