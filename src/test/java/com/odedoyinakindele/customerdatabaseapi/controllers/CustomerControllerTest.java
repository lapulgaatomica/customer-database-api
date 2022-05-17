package com.odedoyinakindele.customerdatabaseapi.controllers;

import com.odedoyinakindele.customerdatabaseapi.Enums.Tariff;
import com.odedoyinakindele.customerdatabaseapi.models.BillingDetail;
import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import com.odedoyinakindele.customerdatabaseapi.payloads.ApiResponse;
import com.odedoyinakindele.customerdatabaseapi.payloads.BillingDetailRequest;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;
    @Autowired
    private JacksonTester<ApiResponse> jsonApiResponse;
    @Autowired
    private JacksonTester<NewCustomerRequest> jsonCustomerRequest;
    @Autowired
    private MockMvc mvc;

    @Test
    void save() throws Exception {
        BillingDetailRequest billingDetailRequest = new BillingDetailRequest("1029389487-01", Tariff.FIRST);
        NewCustomerRequest newCustomerRequest =
                new NewCustomerRequest("firstName", "lastName", "email@email.com", billingDetailRequest);

        BillingDetail billingDetail = new BillingDetail(1L, "1029389487-01", Tariff.FIRST);
        Customer customer = new Customer(1L, "firstName", "lastName", "email@email.com", billingDetail);
        ApiResponse apiResponse = new ApiResponse.ResponseBuilder().setMessage("customer created").setResponseCode(HttpStatus.CREATED.value()).setData(customer).build();
        given(customerService.save(newCustomerRequest)).willReturn(customer);

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/customer").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCustomerRequest.write(
                                newCustomerRequest
                        ).getJson())).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(jsonApiResponse.write(apiResponse).getJson());
    }

    @Test
    void get() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/customer")
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void testGet() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/v1/customer/1")
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}