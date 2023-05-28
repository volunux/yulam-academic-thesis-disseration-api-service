package com.yulam.acalib.model.dto.order;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

  private Set<OrderItemDto> orderItemDtos = new LinkedHashSet<>();
  private Integer totalNumberOfItems;
  private Double totalAmount;
  private Integer totalQuantityOfItems;
}
