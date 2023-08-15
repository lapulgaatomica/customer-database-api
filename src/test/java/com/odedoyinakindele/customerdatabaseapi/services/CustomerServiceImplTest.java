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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BillingDetailRepository billingDetailRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveValidCustomer() {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1234567890-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest = new NewCustomerRequest("John", "Doe", "john@example.com", billingDetailRequest);

        BillingDetail billingDetail = new BillingDetail("1234567890-01", Tariff.FIRST);
        Customer expectedCustomer = new Customer("John", "Doe", "john@example.com", billingDetail);

        when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(billingDetailRepository.findByAccountNumber("1234567890-01")).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(expectedCustomer);

        Customer savedCustomer = customerService.save(newCustomerRequest);

        assertNotNull(savedCustomer);
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Doe", savedCustomer.getLastName());
        assertEquals("john@example.com", savedCustomer.getEmail());
        assertEquals("1234567890-01", savedCustomer.getBillingDetail().getAccountNumber());
        assertEquals(Tariff.FIRST, savedCustomer.getBillingDetail().getTariff());

        verify(customerRepository, times(1)).findByEmail("john@example.com");
        verify(billingDetailRepository, times(1)).findByAccountNumber("1234567890-01");
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testSaveCustomerWithExistingEmail() {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1234567890-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest = new NewCustomerRequest("Jane", "Doe", "john@example.com", billingDetailRequest);

        when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(new Customer()));

        assertThrows(IllegalArgumentException.class, () -> customerService.save(newCustomerRequest));

        verify(customerRepository, times(1)).findByEmail("john@example.com");
        verify(billingDetailRepository, never()).findByAccountNumber(any());
        verify(customerRepository, never()).save(any());
    }

    @Test
    public void testSaveCustomerWithInvalidAccountNumber() {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1234567890-02", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest = new NewCustomerRequest("Alice", "Smith", "alice@example.com", billingDetailRequest);

        assertThrows(IllegalArgumentException.class, () -> customerService.save(newCustomerRequest));

        verify(customerRepository, times(1)).findByEmail("alice@example.com");
        verify(billingDetailRepository, never()).findByAccountNumber(any());
        verify(customerRepository, never()).save(any());
    }

    @Test
    public void testSaveCustomerWithExistingAccountNumber() {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1234567890-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest = new NewCustomerRequest("Bob", "Johnson", "bob@example.com", billingDetailRequest);

        when(customerRepository.findByEmail("bob@example.com")).thenReturn(Optional.empty());
        when(billingDetailRepository.findByAccountNumber("1234567890-01")).thenReturn(Optional.of(new BillingDetail()));

        assertThrows(IllegalArgumentException.class, () -> customerService.save(newCustomerRequest));

        verify(customerRepository, times(1)).findByEmail("bob@example.com");
        verify(billingDetailRepository, times(1)).findByAccountNumber("1234567890-01");
        verify(customerRepository, never()).save(any());
    }

    @Test
    public void testGetCustomerById() {
        long customerId = 1L;
        Customer expectedCustomer = new Customer("John", "Doe", "john@example.com", new BillingDetail("1234567890-01", Tariff.FIRST));

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        Customer retrievedCustomer = customerService.get(customerId);

        assertNotNull(retrievedCustomer);
        assertEquals("John", retrievedCustomer.getFirstName());
        assertEquals("Doe", retrievedCustomer.getLastName());
        assertEquals("john@example.com", retrievedCustomer.getEmail());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> customerService.get(customerId));

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer("John", "Doe", "john@example.com", new BillingDetail("1234567890-01", Tariff.FIRST)));
        expectedCustomers.add(new Customer("Jane", "Smith", "jane@example.com", new BillingDetail("9876543210-01", Tariff.FIRST)));

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<Customer> retrievedCustomers = customerService.get();

        assertNotNull(retrievedCustomers);
        assertEquals(2, retrievedCustomers.size());

        verify(customerRepository, times(1)).findAll();
    }
}
