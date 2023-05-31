package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.MaterialAward;
import com.yulam.acalib.model.dto.MaterialAwardDto;
import com.yulam.acalib.model.mapper.MaterialAwardMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.MaterialAwardView;
import com.yulam.acalib.service.MaterialAwardService;
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
@RequestMapping(value = "/material-award")
public class MaterialAwardController extends ControllerConfig {

  private final MaterialAwardService materialAwardService;

  public MaterialAwardController(MaterialAwardService materialAwardService) {
    this.materialAwardService = materialAwardService;
  }

  @GetMapping(value = "")
  public String home() {
    return "material-award/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<MaterialAward> faculties = materialAwardService.getFaculties();
    List<MaterialAwardView> materialAwardViews = MaterialAwardMapper.toMaterialAwardViews(faculties);

    model.addAttribute("title", "List of Material Award");
    model.addAttribute("faculties", materialAwardViews);
    return "material-award/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    MaterialAward materialAward = materialAwardService.getMaterialAward(id);
    MaterialAwardView materialAwardView = MaterialAwardMapper.toMaterialAwardView(materialAward);

    model.addAttribute("title", "Material Award Detail");
    model.addAttribute("materialAward", materialAwardView);
    return "material-award/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", MaterialAwardDto.builder().build());

    return "material-award/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") MaterialAwardDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "material-award/create";
    }

    materialAwardService.saveMaterialAward(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    MaterialAward materialAward = materialAwardService.getMaterialAward(id);
    MaterialAwardDto dto = MaterialAwardDto.builder()
            .title(materialAward.getTitle())
            .code(materialAward.getCode())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "material-award/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") MaterialAwardDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());

      return "material-award/create";
    }

    materialAwardService.updateMaterialAward(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto ids) {
    materialAwardService.deleteMany(ids);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    materialAwardService.deleteAllMaterialAward();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Material Award";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Material Award";
  }

  @Override
  protected String getEntriesPath() {
    return "/material-award/entries";
  }

  @Override
  protected String getBasePath() {
    return "/materialAward";
  }
}
