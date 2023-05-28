package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.model.dto.faculty.FacultyDto;
import com.yulam.acalib.model.mapper.FacultyMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.faculty.FacultyView;
import com.yulam.acalib.service.FacultyService;
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
@RequestMapping(value = "/faculty")
public class FacultyController extends ControllerConfig {

  private final FacultyService facultyService;

  public FacultyController(FacultyService facultyService) {
    this.facultyService = facultyService;
  }

  @GetMapping(value = "")
  public String home() {
    return "faculty/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Faculty> faculties = facultyService.getFaculties();
    List<FacultyView> facultyViews = FacultyMapper.toFacultyViews(faculties);

    model.addAttribute("title", "List of Faculty");
    model.addAttribute("faculties", facultyViews);
    return "faculty/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Faculty faculty = facultyService.getFaculty(id);
    FacultyView facultyView = FacultyMapper.toFacultyView(faculty);

    model.addAttribute("faculty", facultyView);
    return "faculty/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", FacultyDto.builder().build());

    return "faculty/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") FacultyDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "faculty/create";
    }

    facultyService.saveFaculty(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Faculty faculty = facultyService.getFaculty(id);
    FacultyDto dto = FacultyDto.builder()
            .title(faculty.getTitle())
            .code(faculty.getCode())
            .description(faculty.getDescription())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "faculty/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") FacultyDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());

      return "faculty/create";
    }

    facultyService.updateFaculty(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto ids) {
    facultyService.deleteMany(ids);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    facultyService.deleteAllFaculty();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Faculty";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Faculty";
  }

  @Override
  protected String getEntriesPath() {
    return "/faculty/entries";
  }

  @Override
  protected String getBasePath() {
    return "/faculty";
  }
}
