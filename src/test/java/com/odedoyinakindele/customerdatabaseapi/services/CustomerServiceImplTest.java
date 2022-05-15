package com.odedoyinakindele.customerdatabaseapi.services;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import com.odedoyinakindele.customerdatabaseapi.models.BillingDetail;
import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.BillingDetailRequest;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.repositories.BillingDetailRepository;
import com.odedoyinakindele.customerdatabaseapi.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private BillingDetailRepository billingDetailRepository;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepository, billingDetailRepository);
    }

    @Test
    void shouldSaveWithCorrectInput() {
        //Given
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1029389487-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest =
                new NewCustomerRequest("firstName", "lastName", "email@email.com", billingDetailRequest);
        BillingDetail billingDetail = new BillingDetail(1L, "1029389487-01", Tariff.FIRST);
        Customer customer = new Customer(1L, "firstName", "lastName", "email@email.com", billingDetail);
        given(customerRepository.findByEmail(newCustomerRequest.getEmail()))
                .willReturn(Optional.empty());
        given(billingDetailRepository.findByAccountNumber(billingDetailRequest.getAccountNumber()))
                .willReturn(Optional.empty());

        //When
        customerService.save(newCustomerRequest);

        //Then
        then(customerRepository).should().save(customerArgumentCaptor.capture());
        Customer customerArgumentCaptorValue = customerArgumentCaptor.getValue();
        assertThat(customerArgumentCaptorValue).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void get() {
        //when
        customerService.get();

        verify(customerRepository).findAll();
    }

    @Test
    void testGet() {
        //given
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1029389487-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest =
                new NewCustomerRequest("firstName", "lastName", "email@email.com", billingDetailRequest);
        BillingDetail billingDetail = new BillingDetail(1L, "1029389487-01", Tariff.FIRST);
        Customer customer = new Customer(1L, "firstName", "lastName", "email@email.com", billingDetail);
        given(customerRepository.findById(1L)).willReturn(Optional.of(customer));

        //when
        customerService.get(1L);

        //then
        verify(customerRepository).findById(1L);
    }
}