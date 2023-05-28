package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.PaymentMethod;
import com.yulam.acalib.model.view.PaymentMethodView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodMapper {

  private PaymentMethodMapper() {

  }

  public static PaymentMethodView toPaymentMethodView(@NotNull PaymentMethod paymentMethod) {
    return PaymentMethodView.builder()
            .id(paymentMethod.getId())
            .title(paymentMethod.getTitle())
            .description(paymentMethod.getDescription())
            .createdOn(paymentMethod.getCreatedOn())
            .updatedOn(paymentMethod.getUpdatedOn())
            .build();
  }

  public static List<PaymentMethodView> toPaymentMethodViews(List<PaymentMethod> faculties) {
    if (faculties != null && !faculties.isEmpty()) {
      return faculties
              .stream()
              .map(PaymentMethodMapper::toPaymentMethodView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
