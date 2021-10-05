package com.odedoyinakindele.customerdatabaseapi.controllers;

import com.odedoyinakindele.customerdatabaseapi.payloads.ApiResponse;
import com.odedoyinakindele.customerdatabaseapi.payloads.NewCustomerRequest;
import com.odedoyinakindele.customerdatabaseapi.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid NewCustomerRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse.ResponseBuilder()
                        .setResponseCode(HttpStatus.CREATED.value())
                        .setData(customerService.save(request))
                        .setMessage("customer created").build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> get(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse.ResponseBuilder()
                        .setResponseCode(HttpStatus.OK.value())
                        .setData(customerService.get(id))
                        .setMessage("customer found").build());
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> get(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse.ResponseBuilder()
                        .setResponseCode(HttpStatus.OK.value())
                        .setData(customerService.get())
                        .setMessage("all customers").build());
    }
}
