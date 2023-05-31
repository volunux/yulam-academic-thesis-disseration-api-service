package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.dto.CountryDto;
import com.yulam.acalib.model.mapper.CountryMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.CountryView;
import com.yulam.acalib.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping(value = "/country")
public class CountryController extends ControllerConfig {

  private final CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping(value = "")
  public String home() {
    return "country/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Country> countries = countryService.getCountries();
    List<CountryView> countryViews = CountryMapper.toCountryViews(countries);

    model.addAttribute("title", "List of Country");
    model.addAttribute("countries", countryViews);
    return "country/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Country country = countryService.getCountry(id);
    CountryView countryView = CountryMapper.toCountryView(country);

    model.addAttribute("title", "Country Detail");
    model.addAttribute("country", countryView);
    return "country/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", CountryDto.builder().build());
    return "country/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") CountryDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());
      return "country/create";
    }

    countryService.saveCountry(dto);
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Country country = countryService.getCountry(id);
    CountryDto dto = CountryDto.builder()
            .title(country.getTitle())
            .code(country.getCode())
            .foundingYear(Objects.toString(country.getFoundingYear(), null))
            .mapLogoUrl(country.getMapLogoUrl())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "country/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") CountryDto dto,
                       BindingResult bindingResult,
                       @PathVariable Integer id, Model model) {
    countryService.getCountry(id);
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      return "country/create";
    }

    countryService.updateCountry(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto ids) {
    countryService.deleteMany(ids);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    countryService.deleteAllCountry();
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getBasePath());
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Country";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Country";
  }

  @Override
  protected String getEntriesPath() {
    return "/country/entries";
  }

  @Override
  protected String getBasePath() {
    return "/country";
  }
}
