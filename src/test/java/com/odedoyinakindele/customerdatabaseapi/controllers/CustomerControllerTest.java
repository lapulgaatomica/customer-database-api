package com.odedoyinakindele.customerdatabaseapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.BillingDetailRequest;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testSaveCustomer() throws Exception {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1234567890-01", Tariff.FIRST);
        NewCustomerRequest request = new NewCustomerRequest("John", "Doe", "john@example.com", billingDetailRequest);
        String requestJson = objectMapper.writeValueAsString(request);

        when(customerService.save(any())).thenReturn(new Customer());

        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.responseCode").value(201))
                .andExpect(jsonPath("$.message").value("customer created"))
                .andReturn();
    }

    @Test
    public void testGetCustomerById() throws Exception {
        long customerId = 1L;

        when(customerService.get(customerId)).thenReturn(new Customer());

        mockMvc.perform(get("/api/v1/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(200))
                .andExpect(jsonPath("$.message").value("customer found"))
                .andReturn();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        when(customerService.get()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode").value(200))
                .andExpect(jsonPath("$.message").value("all customers"))
                .andReturn();
    }
}
