package com.yulam.acalib.model.mapper;

import com.yulam.acalib.model.domain.Publisher;
import com.yulam.acalib.model.view.PublisherView;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PublisherMapper {

  private PublisherMapper() {

  }

  public static PublisherView toPublisherView(@NotNull Publisher publisher) {
    return PublisherView.builder()
            .id(publisher.getId())
            .title(publisher.getTitle())
            .createdOn(publisher.getCreatedOn())
            .updatedOn(publisher.getUpdatedOn())
            .build();
  }

  public static List<PublisherView> toPublisherViews(List<Publisher> publishers) {
    if (publishers != null && !publishers.isEmpty()) {
      return publishers
              .stream()
              .map(PublisherMapper::toPublisherView)
              .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

}
