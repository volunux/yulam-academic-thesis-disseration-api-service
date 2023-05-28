package com.yulam.acalib.model.domain;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  private Integer id;

  private Double amount;

  private Material material;

  private Order order;
}
