package com.yulam.acalib.model.dto.order;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

  private Set<OrderItemDto> orderItemDtos;
  private Integer totalNumberOfItems;
  private Double totalAmount;
  private Integer totalQuantityOfItems;
}
