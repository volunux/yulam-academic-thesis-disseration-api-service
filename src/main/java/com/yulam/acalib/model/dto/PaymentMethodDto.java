package com.yulam.acalib.model.dto;

import com.yulam.acalib.model.domain.PaymentMethod;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDto {

  @NotBlank
  @Size(min = 1, max = 200)
  private String title;

  @NotBlank
  @Size(min = 3, max = 5)
  private String description;

  public PaymentMethod toPaymentMethod() {
    return PaymentMethod.builder()
            .title(title)
            .description(description)
            .build();
  }
}
