package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.InstitutionNotFoundException;
import com.yulam.acalib.model.domain.Institution;
import com.yulam.acalib.model.dto.InstitutionDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.InstitutionJpaRepository;
import com.yulam.acalib.service.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstitutionServiceImpl implements InstitutionService {

  private final InstitutionJpaRepository institutionJpaRepository;

  public InstitutionServiceImpl(InstitutionJpaRepository institutionJpaRepository) {
    this.institutionJpaRepository = institutionJpaRepository;
  }

  @Override
  public Institution getInstitution(Integer id) {
    return institutionJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Institution does not exists or cannot be found. ID: %d", id);
              return new InstitutionNotFoundException(msg);
            });
  }

  @Override
  public List<Institution> getInstitutions() {
    return institutionJpaRepository.findAll();
  }

  @Override
  public Institution saveInstitution(InstitutionDto dto) {
    Institution institution = dto.toInstitution();
    return institutionJpaRepository.save(institution);
  }

  @Override
  public Institution updateInstitution(Integer id, InstitutionDto dto) {
    getInstitution(id);
    Institution institution = dto.toInstitution();
    institution.setId(id);
    return institutionJpaRepository.save(institution);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Institution> institutions = dto
            .getIds()
            .stream()
            .map(id -> Institution.builder()
                    .id(id).build())
            .collect(Collectors.toList());
    institutionJpaRepository.deleteAll(institutions);
  }

  @Override
  public void deleteAllInstitution() {
    institutionJpaRepository.deleteAll();
  }

  @Override
  public boolean isInstitutionExists(Integer id) {
    return institutionJpaRepository.findById(id).isPresent();
  }

}
