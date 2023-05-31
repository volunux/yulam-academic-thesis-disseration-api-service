package com.yulam.acalib.startup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulam.acalib.exception.StateDuplicateEntityException;
import com.yulam.acalib.json.StateJson;
import com.yulam.acalib.model.domain.Country;
import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.model.dto.StateDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.CountryJpaRepository;
import com.yulam.acalib.repository.jpa.StateJpaRepository;
import com.yulam.acalib.service.StateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.yulam.acalib.util.AcalibUtil.readResourceFile;

@Slf4j
@Service
@AllArgsConstructor
public class StateStartupService implements StateService {

  private final StateJpaRepository jpaRepository;
  private final CountryJpaRepository countryJpaRepository;
  private final ObjectMapper objectMapper;
  private final static String COUNTRY_NAME = "Nigeria";

  @Override
  @Transactional
  @EventListener(ApplicationReadyEvent.class)
  public void seedRecords() throws JsonProcessingException {
    String statesJsonString = readResourceFile("json.state/nigeria.json");
    List<StateJson> states = objectMapper.readValue(statesJsonString, new TypeReference<>() {});
    Country country = countryJpaRepository.findByTitle(COUNTRY_NAME);

    try {
      for (StateJson stateJson : states) {
        Optional<State> stateExists = jpaRepository.findByTitle(stateJson.getInfo().getOfficialName());

        if (stateExists.isPresent()) {
          continue;
        }
        State state = State.builder()
                .title(stateJson.getInfo().getOfficialName())
                .capital(stateJson.getInfo().getCapital())
                .foundingYear(stateJson.getInfo().getDateCreated().getYear())
                .country(country)
                .build();
        jpaRepository.save(state);
      }
    } catch (StateDuplicateEntityException ignored) { }
  }

  @Override
  public State getState(Integer id) {
    return null;
  }

  @Override
  public List<State> getStates() {
    return null;
  }

  @Override
  public State saveState(StateDto dto) {
    return null;
  }

  @Override
  public State updateState(Integer id, StateDto dto) {
    return null;
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {

  }

  @Override
  public void deleteAllState() {

  }

  @Override
  public boolean isStateExists(Integer id) {
    return false;
  }

}
