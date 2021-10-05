package com.odedoyinakindele.customerdatabaseapi.repositories;

import com.odedoyinakindele.customerdatabaseapi.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
