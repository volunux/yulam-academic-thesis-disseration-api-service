package com.yulam.acalib.controller;

import com.yulam.acalib.model.domain.Publisher;
import com.yulam.acalib.model.dto.PublisherDto;
import com.yulam.acalib.model.mapper.PublisherMapper;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.model.response.other.DeleteResponse;
import com.yulam.acalib.model.view.PublisherView;
import com.yulam.acalib.service.PublisherService;
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
@RequestMapping(value = "/publisher")
public class PublisherController extends ControllerConfig {

  private final PublisherService publisherService;

  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping
  public String home() {
    return "publisher/index";
  }

  @GetMapping(value = "/entries")
  public String findAll(Model model) {
    List<Publisher> publishers = publisherService.getPublishers();
    List<PublisherView> publisherViews = PublisherMapper.toPublisherViews(publishers);

    model.addAttribute("title", "List of Publisher");
    model.addAttribute("publishers", publisherViews);
    return "publisher/list";
  }

  @GetMapping(value = "/detail/{id}")
  public String findOne(@PathVariable Integer id, Model model) {
    Publisher publisher = publisherService.getPublisher(id);
    PublisherView publisherView = PublisherMapper.toPublisherView(publisher);

    model.addAttribute("title", "Publisher Detail");
    model.addAttribute("publisher", publisherView);

    return "publisher/detail";
  }

  @GetMapping(value = "/create")
  public String add(Model model) {

    model.addAttribute("title", getCreateViewTitle());
    model.addAttribute("formData", PublisherDto.builder().build());
    return "publisher/create";
  }

  @PostMapping(value = "/create")
  public String save(@Valid @ModelAttribute("formData") PublisherDto dto,
                     BindingResult bindingResult, Model model,
                     RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getCreateViewTitle());

      return "publisher/create";
    }

    publisherService.savePublisher(dto);

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getEntriesPath());
  }

  @GetMapping(value = "/update/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Publisher publisher = publisherService.getPublisher(id);
    PublisherDto dto = PublisherDto.builder()
            .title(publisher.getTitle())
            .build();

    model.addAttribute("title", getUpdateViewTitle());
    model.addAttribute("formData", dto);
    return "publisher/create";
  }

  @PostMapping(value = "/update/{id}")
  public String update(@Valid @ModelAttribute("formData") PublisherDto dto,
                       @PathVariable Integer id, Model model,
                       BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("title", getUpdateViewTitle());
      return "publisher/create";
    }

    publisherService.updatePublisher(id, dto);
    return "redirect:".concat(getEntriesPath());
  }

  @ResponseBody
  @PostMapping(value ="/delete-many", consumes = { MediaType.APPLICATION_JSON_VALUE })
  public DeleteResponse deleteMany(@Valid @RequestBody DeleteIdsDto dto) {
    publisherService.deleteMany(dto);
    return new DeleteResponse("Success", true);
  }

  @PostMapping(value ="/delete-all")
  public String deleteAll(RedirectAttributes redirectAttributes) {
    publisherService.deleteAllPublisher();

    redirectAttributes.addFlashAttribute(getFormProcessedMessageKey(), "Success");
    return "redirect:".concat(getBasePath());
  }


  @Override
  protected String getCreateViewTitle() {
    return "Create a Publisher";
  }

  @Override
  protected String getUpdateViewTitle() {
    return "Update a Publisher";
  }

  @Override
  protected String getEntriesPath() {
    return "/publisher/entries";
  }

  @Override
  protected String getBasePath() {
    return "/publisher";
  }
}
