package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.MemberStatus;
import com.yulam.acalib.model.dto.MemberStatusDto;
import com.yulam.acalib.model.mapper.MemberStatusMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.MemberStatusView;
import com.yulam.acalib.service.MemberStatusService;
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
@RequestMapping(value = "/member-status")
public class MemberStatusController extends ControllerConfig {

  private final MemberStatusService memberStatusService;

  public MemberStatusController(MemberStatusService memberStatusService) {
    this.memberStatusService = memberStatusService;
  }

  @GetMapping
  public String home() {
    return "member-status/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<MemberStatus> memberStatuss = memberStatusService.getMemberStatuses();
    List<MemberStatusView> memberStatusViews = MemberStatusMapper.toMemberStatusViews(memberStatuss);

    model.addAttribute("title", "List of Member Status");
    model.addAttribute("memberStatuss", memberStatusViews);
    return "member-status/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    MemberStatus memberStatus = memberStatusService.getMemberStatus(id);
    MemberStatusView memberStatusView = MemberStatusMapper.toMemberStatusView(memberStatus);

    model.addAttribute("title", "Member Status Detail");
    model.addAttribute("memberStatus", memberStatusView);

    return "member-status/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {

    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", MemberStatusDto.builder().build());
    return "member-status/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") MemberStatusDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "member-status/create";
    }

    memberStatusService.saveMemberStatus(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    MemberStatus memberStatus = memberStatusService.getMemberStatus(id);
    MemberStatusDto dto = MemberStatusDto.builder()
            .title(memberStatus.getTitle())
            .description(memberStatus.getDescription())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "member-status/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") MemberStatusDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      return "member-status/create";
    }

    memberStatusService.updateMemberStatus(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto dto) {
    memberStatusService.deleteMany(dto);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    memberStatusService.deleteAllMemberStatus();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }


  @Override
  protected String getCreateViewTitle() {
    return "Create a Member Status";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Member Status";
  }

  @Override
  protected String getEntriesPath() {
    return "/member-status/entries";
  }

  @Override
  protected String getBasePath() {
    return "/member-status";
  }
}
