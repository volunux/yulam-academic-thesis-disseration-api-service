package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.PublisherNotFoundException;
import com.yulam.acalib.model.domain.Publisher;
import com.yulam.acalib.model.dto.PublisherDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.PublisherJpaRepository;
import com.yulam.acalib.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PublisherServiceImpl implements PublisherService {

  private final PublisherJpaRepository publisherJpaRepository;

  public PublisherServiceImpl(PublisherJpaRepository publisherJpaRepository) {
    this.publisherJpaRepository = publisherJpaRepository;
  }

  @Override
  public Publisher getPublisher(Integer id) {
    return publisherJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Publisher does not exists or cannot be found. ID: %d", id);
              return new PublisherNotFoundException(msg);
            });
  }

  @Override
  public List<Publisher> getPublishers() {
    return publisherJpaRepository.findAll();
  }

  @Override
  public Publisher savePublisher(PublisherDto dto) {
    Publisher Publisher = dto.toPublisher();
    return publisherJpaRepository.save(Publisher);
  }

  @Override
  public Publisher updatePublisher(Integer id, PublisherDto dto) {
    getPublisher(id);
    Publisher Publisher = dto.toPublisher();
    Publisher.setId(id);
    return publisherJpaRepository.save(Publisher);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Publisher> publishers = dto
            .getIds()
            .stream()
            .map(id -> Publisher.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    publisherJpaRepository.deleteAll(publishers);
  }

  @Override
  public void deleteAllPublisher() {
    publisherJpaRepository.deleteAll();
  }

  @Override
  public boolean isPublisherExists(Integer id) {
    System.out.println("Checking publisher id : " + id);
    return publisherJpaRepository.findById(id).isPresent();
  }

}
