package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Institution;
import com.yulam.acalib.model.dto.InstitutionDto;
import com.yulam.acalib.model.mapper.InstitutionMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.InstitutionView;
import com.yulam.acalib.service.InstitutionService;
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
@RequestMapping(value = "/institution")
public class InstitutionController extends ControllerConfig {

  private final InstitutionService institutionService;

  public InstitutionController(InstitutionService institutionService) {
    this.institutionService = institutionService;
  }

  @GetMapping
  public String home() {
    return "institution/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Institution> institutions = institutionService.getInstitutions();
    List<InstitutionView> institutionViews = InstitutionMapper.toInstitutionViews(institutions);

    model.addAttribute("title", "List of Institution");
    model.addAttribute("institutions", institutionViews);
    return "institution/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Institution institution = institutionService.getInstitution(id);
    InstitutionView institutionView = InstitutionMapper.toInstitutionView(institution);

    model.addAttribute("title", "Institution Detail");
    model.addAttribute("institution", institutionView);
    return "institution/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", InstitutionDto.builder().build());
    return "institution/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") InstitutionDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());
      return "institution/create";
    }

    institutionService.saveInstitution(dto);
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Institution institution = institutionService.getInstitution(id);
    InstitutionDto dto = InstitutionDto.builder()
            .title(institution.getTitle())
            .state(String.valueOf(institution.getState().getId()))
            .country(String.valueOf(institution.getCountry().getId()))
            .location(institution.getLocation())
            .foundingYear(Objects.toString(institution.getFoundingYear(), null))
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "institution/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") InstitutionDto dto,
                       BindingResult bindingResult,
                       @PathVariable Integer id, Model model) {
    institutionService.getInstitution(id);
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      return "institution/create";
    }

    institutionService.updateInstitution(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto dto) {
    institutionService.deleteMany(dto);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    institutionService.deleteAllInstitution();
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getBasePath());
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Institution";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Institution";
  }

  @Override
  protected String getEntriesPath() {
    return "/institution/entries";
  }

  @Override
  protected String getBasePath() {
    return "/institution";
  }
}
