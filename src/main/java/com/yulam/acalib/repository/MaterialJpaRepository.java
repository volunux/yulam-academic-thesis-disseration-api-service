package com.yulam.acalib.repository;

import com.yulam.acalib.model.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialJpaRepository extends JpaRepository<Material, Integer> {
}
