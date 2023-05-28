package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country, Integer> {

  Country findByTitle(String title);
}
