package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

  List<Faculty> getFaculties();

  Optional<Faculty> getFaculty(Integer id);
}
