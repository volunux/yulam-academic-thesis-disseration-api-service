package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Faculty;
import com.yulam.acalib.model.dto.faculty.FacultyDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;

import java.util.List;

public interface FacultyService {

  Faculty getFaculty(Integer id);

  List<Faculty> getFaculties();
  
  Faculty saveFaculty(FacultyDto dto);

  Faculty updateFaculty(Integer id, FacultyDto dto);

  boolean deleteMany(DeleteIdsDto ids);

  boolean deleteAllFaculty();

  boolean isFacultyExists(Integer id);
}
