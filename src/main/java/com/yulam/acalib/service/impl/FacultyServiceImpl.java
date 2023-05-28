package com.yulam.acalib.service.impl;

import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.repository.jpa.FacultyJpaRepository;
import com.yulam.acalib.service.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FacultyServiceImpl implements FacultyService {

  private final FacultyJpaRepository facultyJpaRepository;

  public FacultyServiceImpl(FacultyJpaRepository facultyJpaRepository) {
    this.facultyJpaRepository = facultyJpaRepository;
  }

  @Override
  public List<Faculty> getFaculties() {
    return facultyJpaRepository.findAll();
  }

  @Override
  public Optional<Faculty> getFaculty(Integer id) {
    return facultyJpaRepository.findById(id);
  }
}
