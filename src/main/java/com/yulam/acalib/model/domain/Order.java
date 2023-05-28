package com.yulam.acalib.model.domain;


import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  private Integer id;

  private Integer totalQuantity;

  private Double amount;

  private String orderReference;

  private OrderStatus orderStatus;

  private DeliveryMethod deliveryMethod;

  private Country country;

  private PaymentMethod paymentMethod;

  private Set<OrderItem> orderItems;

  private String state;

  private String city;

  private String address;

  private String zip;

  private String phoneNumber;
}
