package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.FacultyNotFoundException;
import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.model.dto.FacultyDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.FacultyJpaRepository;
import com.yulam.acalib.service.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FacultyServiceImpl implements FacultyService {

  private final FacultyJpaRepository facultyJpaRepository;

  public FacultyServiceImpl(FacultyJpaRepository facultyJpaRepository) {
    this.facultyJpaRepository = facultyJpaRepository;
  }

  @Override
  public Faculty getFaculty(Integer id) {
    return facultyJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Faculty does not exists or cannot be found. ID: %d", id);
              return new FacultyNotFoundException(msg);
            });
  }

  @Override
  public List<Faculty> getFaculties() {
    return facultyJpaRepository.findAll();
  }

  @Override
  public Faculty saveFaculty(FacultyDto dto) {
    Faculty Faculty = dto.toFaculty();
    return facultyJpaRepository.save(Faculty);
  }

  @Override
  public Faculty updateFaculty(Integer id, FacultyDto dto) {
    getFaculty(id);
    Faculty Faculty = dto.toFaculty();
    Faculty.setId(id);
    return facultyJpaRepository.save(Faculty);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Faculty> faculties = dto
            .getIds()
            .stream()
            .map(id -> Faculty.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    facultyJpaRepository.deleteAll(faculties);
  }

  @Override
  public void deleteAllFaculty() {
    facultyJpaRepository.deleteAll();
  }

  @Override
  public boolean isFacultyExists(Integer id) {
    System.out.println("Checking faculty id : " + id);
    return facultyJpaRepository.findById(id).isPresent();
  }
  
}
