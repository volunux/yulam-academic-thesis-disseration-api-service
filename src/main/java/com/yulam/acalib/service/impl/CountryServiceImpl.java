package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.CountryNotFoundException;
import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.dto.country.CountryDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.CountryJpaRepository;
import com.yulam.acalib.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

  private final CountryJpaRepository countryJpaRepository;

  public CountryServiceImpl(CountryJpaRepository countryJpaRepository) {
    this.countryJpaRepository = countryJpaRepository;
  }

  @Override
  public Country getCountry(Integer id) {
    return countryJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Country does not exists or cannot be found. ID: %d", id);
              return new CountryNotFoundException(msg);
            });
  }

  @Override
  public List<Country> getFaculties() {
    return countryJpaRepository.findAll();
  }

  @Override
  public Country saveCountry(CountryDto dto) {
    Country Country = dto.toCountry();
    return countryJpaRepository.save(Country);
  }

  @Override
  public Country updateCountry(Integer id, CountryDto dto) {
    getCountry(id);
    Country Country = dto.toCountry();
    Country.setId(id);
    return countryJpaRepository.save(Country);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Country> countries = dto
            .getIds()
            .stream()
            .map(id -> Country.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    countryJpaRepository.deleteAll(countries);
  }

  @Override
  public void deleteAllCountry() {
    countryJpaRepository.deleteAll();
  }

  @Override
  public boolean isCountryExists(Integer id) {
    System.out.println("Checking country id : " + id);
    return countryJpaRepository.findById(id).isPresent();
  }

}
