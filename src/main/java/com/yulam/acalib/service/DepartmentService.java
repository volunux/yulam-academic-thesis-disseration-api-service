package com.yulam.acalib.service;

import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.dto.department.DepartmentDto;

import java.util.List;

public interface DepartmentService {

  Department getDepartment(Integer id);

  List<Department> getDepartments();

  Department saveDepartment(DepartmentDto dto);

  Department updateDepartment(Integer id, DepartmentDto dto);

  boolean deleteMany(List<Integer> ids);

  boolean deleteAllDepartment();
}
