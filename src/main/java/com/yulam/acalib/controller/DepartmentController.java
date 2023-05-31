package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.model.dto.DepartmentDto;
import com.yulam.acalib.model.mapper.DepartmentMapper;
import com.yulam.acalib.model.mapper.FacultyMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.DepartmentView;
import com.yulam.acalib.model.view.FacultyView;
import com.yulam.acalib.service.DepartmentService;
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
@RequestMapping(value = "/department")
public class DepartmentController extends ControllerConfig {

  private final DepartmentService departmentService;
  private final FacultyService facultyService;

  public DepartmentController(DepartmentService departmentService, FacultyService facultyService) {
    this.departmentService = departmentService;
    this.facultyService = facultyService;
  }

  @GetMapping
  public String home() {
    return "department/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Department> departments = departmentService.getDepartments();
    List<DepartmentView> departmentViews = DepartmentMapper.toDepartmentViews(departments);

    model.addAttribute("title", "List of Department");
    model.addAttribute("departments", departmentViews);
    return "department/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Department department = departmentService.getDepartment(id);
    DepartmentView departmentView = DepartmentMapper.toDepartmentView(department);

    model.addAttribute("title", "Department Detail");
    model.addAttribute("department", departmentView);
    return "department/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {
    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("faculties", getFacultyViews());
    model.addAttribute("formData", DepartmentDto.builder().build());
    return "department/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") DepartmentDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());
      model.addAttribute("faculties", getFacultyViews());
      return "department/create";
    }

    departmentService.saveDepartment(dto);
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {
    Department department = departmentService.getDepartment(id);
    DepartmentDto dto = DepartmentDto.builder()
                    .title(department.getTitle())
                    .code(department.getCode())
                    .faculty(String.valueOf(department.getFaculty().getId()))
                    .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("faculties", getFacultyViews());
    model.addAttribute("formData", dto);
    return "department/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") DepartmentDto dto,
                       BindingResult bindingResult,
                       @PathVariable Integer id, Model model) {
    departmentService.getDepartment(id);
    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      model.addAttribute("faculties", getFacultyViews());
      return "department/create";
    }

    departmentService.updateDepartment(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto dto) {
    departmentService.deleteMany(dto);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    departmentService.deleteAllDepartment();
    this.addFormProcessedAttribute(redirectAttributes);
    return "redirect:".concat(getBasePath());
  }

  private List<FacultyView> getFacultyViews() {
    List<Faculty> faculties = facultyService.getFaculties();
    return FacultyMapper.toFacultyViews(faculties);
  }

  @Override
  protected String getCreateViewTitle() {
    return "Create a Department";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Department";
  }

  @Override
  protected String getEntriesPath() {
    return "/department/entries";
  }

  @Override
  protected String getBasePath() {
    return "/department";
  }
}
