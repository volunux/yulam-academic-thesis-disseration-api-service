package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.PaymentMethod;
import com.yulam.acalib.model.dto.PaymentMethodDto;
import com.yulam.acalib.model.mapper.PaymentMethodMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.PaymentMethodView;
import com.yulam.acalib.service.PaymentMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/payment-method")
public class PaymentMethodController extends ControllerConfig {

  private final PaymentMethodService paymentMethodService;

  public PaymentMethodController(PaymentMethodService paymentMethodService) {
    this.paymentMethodService = paymentMethodService;
  }

  @GetMapping(value = "")
  public String home() {
    return "payment-method/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<PaymentMethod> paymentMethods = paymentMethodService.getFaculties();
    List<PaymentMethodView> paymentMethodViews = PaymentMethodMapper.toPaymentMethodViews(paymentMethods);

    model.addAttribute("title", "List of Payment Method");
    model.addAttribute("paymentMethods", paymentMethodViews);
    return "payment-method/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    PaymentMethod paymentMethod = paymentMethodService.getPaymentMethod(id);
    PaymentMethodView paymentMethodView = PaymentMethodMapper.toPaymentMethodView(paymentMethod);

    model.addAttribute("paymentMethod", paymentMethodView);
    return "payment-method/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", PaymentMethodDto.builder().build());

    return "payment-method/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") PaymentMethodDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "payment-method/create";
    }

    paymentMethodService.savePaymentMethod(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    PaymentMethod paymentMethod = paymentMethodService.getPaymentMethod(id);
    PaymentMethodDto dto = PaymentMethodDto.builder()
            .title(paymentMethod.getTitle())
            .description(paymentMethod.getDescription())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "payment-method/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") PaymentMethodDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());

      return "payment-method/create";
    }

    paymentMethodService.updatePaymentMethod(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto ids) {
    paymentMethodService.deleteMany(ids);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    paymentMethodService.deleteAllPaymentMethod();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Payment Method";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Payment Method";
  }

  @Override
  protected String getEntriesPath() {
    return "/payment-method/entries";
  }

  @Override
  protected String getBasePath() {
    return "/payment-method";
  }
}
