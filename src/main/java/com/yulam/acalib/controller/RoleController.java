package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Role;
import com.yulam.acalib.model.dto.RoleDto;
import com.yulam.acalib.model.mapper.RoleMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.RoleView;
import com.yulam.acalib.service.RoleService;
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
@RequestMapping(value = "/role")
public class RoleController extends ControllerConfig {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public String home() {
    return "role/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Role> roles = roleService.getRoles();
    List<RoleView> roleViews = RoleMapper.toRoleViews(roles);

    model.addAttribute("title", "List of Role");
    model.addAttribute("roles", roleViews);
    return "role/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Role role = roleService.getRole(id);
    RoleView roleView = RoleMapper.toRoleView(role);

    model.addAttribute("title", "Role Detail");
    model.addAttribute("role", roleView);

    return "role/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {

    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", RoleDto.builder().build());
    return "role/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") RoleDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "role/create";
    }

    roleService.saveRole(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Role role = roleService.getRole(id);
    RoleDto dto = RoleDto.builder()
            .title(role.getTitle())
            .description(role.getDescription())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "role/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") RoleDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      return "role/create";
    }

    roleService.updateRole(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto dto) {
    roleService.deleteMany(dto);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    roleService.deleteAllRole();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }


  @Override
  protected String getCreateViewTitle() {
    return "Create a Role";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Role";
  }

  @Override
  protected String getEntriesPath() {
    return "/role/entries";
  }

  @Override
  protected String getBasePath() {
    return "/role";
  }
}
