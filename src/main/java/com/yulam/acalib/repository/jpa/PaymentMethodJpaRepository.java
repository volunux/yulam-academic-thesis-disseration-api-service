package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodJpaRepository extends JpaRepository<PaymentMethod, Integer> {
}
