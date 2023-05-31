package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Publisher;
import com.yulam.acalib.model.dto.PublisherDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface PublisherService {

  Publisher getPublisher(Integer id);

  List<Publisher> getPublishers();

  Publisher savePublisher(PublisherDto dto);

  Publisher updatePublisher(Integer id, PublisherDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllPublisher();

  boolean isPublisherExists(Integer id);
}
