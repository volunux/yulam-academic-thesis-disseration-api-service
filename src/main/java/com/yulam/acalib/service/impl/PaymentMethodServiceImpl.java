package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.PaymentMethodNotFoundException;
import com.yulam.acalib.model.domain.PaymentMethod;
import com.yulam.acalib.model.dto.PaymentMethodDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.PaymentMethodJpaRepository;
import com.yulam.acalib.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

  private final PaymentMethodJpaRepository paymentMethodJpaRepository;

  public PaymentMethodServiceImpl(PaymentMethodJpaRepository paymentMethodJpaRepository) {
    this.paymentMethodJpaRepository = paymentMethodJpaRepository;
  }

  @Override
  public PaymentMethod getPaymentMethod(Integer id) {
    return paymentMethodJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Payment Method does not exists or cannot be found. ID: %d", id);
              return new PaymentMethodNotFoundException(msg);
            });
  }

  @Override
  public List<PaymentMethod> getFaculties() {
    return paymentMethodJpaRepository.findAll();
  }

  @Override
  public PaymentMethod savePaymentMethod(PaymentMethodDto dto) {
    PaymentMethod PaymentMethod = dto.toPaymentMethod();
    return paymentMethodJpaRepository.save(PaymentMethod);
  }

  @Override
  public PaymentMethod updatePaymentMethod(Integer id, PaymentMethodDto dto) {
    getPaymentMethod(id);
    PaymentMethod PaymentMethod = dto.toPaymentMethod();
    PaymentMethod.setId(id);
    return paymentMethodJpaRepository.save(PaymentMethod);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<PaymentMethod> countries = dto
            .getIds()
            .stream()
            .map(id -> PaymentMethod.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    paymentMethodJpaRepository.deleteAll(countries);
  }

  @Override
  public void deleteAllPaymentMethod() {
    paymentMethodJpaRepository.deleteAll();
  }

  @Override
  public boolean isPaymentMethodExists(Integer id) {
    System.out.println("Checking paymentMethod id : " + id);
    return paymentMethodJpaRepository.findById(id).isPresent();
  }

}
