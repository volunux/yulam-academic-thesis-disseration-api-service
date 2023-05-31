package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Institution;
import com.yulam.acalib.model.dto.InstitutionDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface InstitutionService {

  Institution getInstitution(Integer id);

  List<Institution> getInstitutions();

  Institution saveInstitution(InstitutionDto dto);

  Institution updateInstitution(Integer id, InstitutionDto dto);

  void deleteMany(DeleteIdsDto ids);

  void deleteAllInstitution();

  boolean isInstitutionExists(Integer id);
}
