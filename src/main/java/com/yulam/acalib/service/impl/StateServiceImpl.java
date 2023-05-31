package com.yulam.acalib.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yulam.acalib.exception.StateNotFoundException;
import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.model.dto.StateDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.StateJpaRepository;
import com.yulam.acalib.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StateServiceImpl implements StateService {

  private final StateJpaRepository stateJpaRepository;

  public StateServiceImpl(StateJpaRepository stateJpaRepository) {
    this.stateJpaRepository = stateJpaRepository;
  }

  @Override
  public State getState(Integer id) {
    return stateJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("State does not exists or cannot be found. ID: %d", id);
              return new StateNotFoundException(msg);
            });
  }

  @Override
  public List<State> getStates() {
    return stateJpaRepository.findAll();
  }

  @Override
  public State saveState(StateDto dto) {
    State state = dto.toState();
    return stateJpaRepository.save(state);
  }

  @Override
  public State updateState(Integer id, StateDto dto) {
    getState(id);
    State state = dto.toState();
    state.setId(id);
    return stateJpaRepository.save(state);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<State> states = dto
            .getIds()
            .stream()
            .map(id -> State.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    stateJpaRepository.deleteAll(states);
  }

  @Override
  public void deleteAllState() {
    stateJpaRepository.deleteAll();
  }

  @Override
  public boolean isStateExists(Integer id) {
    return stateJpaRepository.findById(id).isPresent();
  }

  @Override
  public void seedRecords() throws JsonProcessingException {

  }

}
