package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.MaterialNotFoundException;
import com.yulam.acalib.model.domain.Material;
import com.yulam.acalib.repository.MaterialJpaRepository;
import com.yulam.acalib.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

  private final MaterialJpaRepository materialJpaRepository;

  public MaterialServiceImpl(MaterialJpaRepository materialJpaRepository) {
    this.materialJpaRepository = materialJpaRepository;
  }

  @Override
  public Material getMaterial(Integer id) {
    return materialJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Material does not exists or cannot be found. ID: %d", id);
              return new MaterialNotFoundException(msg);
            });
  }
}
