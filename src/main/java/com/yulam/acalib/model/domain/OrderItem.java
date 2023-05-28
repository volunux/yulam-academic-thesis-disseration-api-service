package com.yulam.acalib.model.domain;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  private Integer id;

  private Integer quantity;

  private Double amount;

  private Double unitAmount;

  private Material material;

  private Order order;
}
