package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.PaymentMethod;
import com.yulam.acalib.model.dto.PaymentMethodDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface PaymentMethodService {

  PaymentMethod getPaymentMethod(Integer id);

  List<PaymentMethod> getFaculties();

  PaymentMethod savePaymentMethod(PaymentMethodDto dto);

  PaymentMethod updatePaymentMethod(Integer id, PaymentMethodDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllPaymentMethod();

  boolean isPaymentMethodExists(Integer id);
}
