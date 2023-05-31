package com.yulam.acalib.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yulam.acalib.model.domain.State;
import com.yulam.acalib.model.dto.StateDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface StateService {

  void seedRecords() throws JsonProcessingException;

  State getState(Integer id);

  List<State> getStates();

  State saveState(StateDto dto);

  State updateState(Integer id, StateDto dto);

  void deleteMany(DeleteIdsDto dto);

  void deleteAllState();

  boolean isStateExists(Integer id);
}
