package com.odedoyinakindele.customerdatabaseapi.repositories;

import com.odedoyinakindele.customerdatabaseapi.models.BillingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingDetailRepository extends JpaRepository<BillingDetail, Long> {
    Optional<BillingDetail> findByAccountNumber(String accountNumber);
}
