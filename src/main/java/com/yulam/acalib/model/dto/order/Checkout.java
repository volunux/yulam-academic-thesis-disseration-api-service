package com.yulam.acalib.model.dto.order;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {

  private String fullName;
  private String phoneNumber;
  private Integer country;
  private String state;
  private String city;
  private String contactAddress;
  private String zip;
  private Integer paymentMethod;

}
