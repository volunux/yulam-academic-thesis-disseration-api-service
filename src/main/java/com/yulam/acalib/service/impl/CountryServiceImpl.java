package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.CountryNotFoundException;
import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.dto.CountryDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.CountryJpaRepository;
import com.yulam.acalib.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
              String message = String.format("Country does not exists or cannot be found. ID: %d", id);
              return new CountryNotFoundException(message);
            });
  }

  @Override
  public List<Country> getCountries() {
    return countryJpaRepository.findAll();
  }

  @Override
  @Transactional
  public Country saveCountry(CountryDto dto) {
    Country country = dto.toCountry();
    System.out.println("The country id is : " + country.getId());
    return countryJpaRepository.save(country);
  }

  @Override
  @Transactional
  public Country updateCountry(Integer id, CountryDto dto) {
    getCountry(id);
    Country country = dto.toCountry();
    country.setId(id);

    return countryJpaRepository.save(country);
  }

  @Override
  @Transactional
  public void deleteMany(DeleteIdsDto dto) {
    List<Country> countries = dto
            .getIds()
            .stream()
            .map(id -> Country.builder()
                    .id(id).build())
            .collect(Collectors.toList());

    countryJpaRepository.deleteAll(countries);
  }

  @Override
  public void deleteAllCountry() {
    countryJpaRepository.deleteAll();
  }

  @Override
  public boolean isCountryExists(Integer id) {
    return countryJpaRepository.findById(id).isPresent();
  }

}
