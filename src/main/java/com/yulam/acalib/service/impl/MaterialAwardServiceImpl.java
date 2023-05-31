package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.MaterialAwardNotFoundException;
import com.yulam.acalib.model.domain.MaterialAward;
import com.yulam.acalib.model.dto.MaterialAwardDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.MaterialAwardJpaRepository;
import com.yulam.acalib.service.MaterialAwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MaterialAwardServiceImpl implements MaterialAwardService {

  private final MaterialAwardJpaRepository materialAwardJpaRepository;

  public MaterialAwardServiceImpl(MaterialAwardJpaRepository materialAwardJpaRepository) {
    this.materialAwardJpaRepository = materialAwardJpaRepository;
  }

  @Override
  public MaterialAward getMaterialAward(Integer id) {
    return materialAwardJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("MaterialAward does not exists or cannot be found. ID: %d", id);
              return new MaterialAwardNotFoundException(msg);
            });
  }

  @Override
  public List<MaterialAward> getFaculties() {
    return materialAwardJpaRepository.findAll();
  }

  @Override
  public MaterialAward saveMaterialAward(MaterialAwardDto dto) {
    MaterialAward MaterialAward = dto.toMaterialAward();
    return materialAwardJpaRepository.save(MaterialAward);
  }

  @Override
  public MaterialAward updateMaterialAward(Integer id, MaterialAwardDto dto) {
    getMaterialAward(id);
    MaterialAward MaterialAward = dto.toMaterialAward();
    MaterialAward.setId(id);
    return materialAwardJpaRepository.save(MaterialAward);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<MaterialAward> materialAwards = dto
            .getIds()
            .stream()
            .map(id -> MaterialAward.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    materialAwardJpaRepository.deleteAll(materialAwards);
  }

  @Override
  public void deleteAllMaterialAward() {
    materialAwardJpaRepository.deleteAll();
  }

  @Override
  public boolean isMaterialAwardExists(Integer id) {
    System.out.println("Checking materialAward id : " + id);
    return materialAwardJpaRepository.findById(id).isPresent();
  }

}
