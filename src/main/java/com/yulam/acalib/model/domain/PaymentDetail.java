package com.yulam.acalib.model.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {

  private Integer cardLastNumbers;

  private Integer cardExpirationYear;

  private Integer cardExpirationMonth;

  private String bankName;

  private String cardType;

  private Member member;
}
