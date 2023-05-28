package com.yulam.acalib.controller;

import com.yulam.acalib.model.dto.order.OrderItemDto;
import com.yulam.acalib.model.dto.order.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "")
@SessionAttributes("shoppingCart")
public class ShoppingCartController {

  @ModelAttribute("shoppingCart")
  public ShoppingCart shoppingCart() {
    return new ShoppingCart();
  }

  @PostMapping(value = "add-item")
  public void addItem(@Valid OrderItemDto dto,
                      @ModelAttribute ShoppingCart cart) {
    cart.getOrderItemDtos().add(dto);
  }

  @PostMapping(value = "process")
  public void processOrder(@Valid ShoppingCart cart, BindingResult bindingResult, SessionStatus sessionStatus) {
    sessionStatus.setComplete();
  }
}
