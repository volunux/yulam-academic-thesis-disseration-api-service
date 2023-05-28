package com.yulam.acalib.service.impl;

import com.yulam.acalib.exception.DepartmentNotFoundException;
import com.yulam.acalib.model.domain.Department;
import com.yulam.acalib.model.dto.department.DepartmentDto;
import com.yulam.acalib.model.response.other.DeleteIdsDto;
import com.yulam.acalib.repository.jpa.DepartmentJpaRepository;
import com.yulam.acalib.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentJpaRepository departmentJpaRepository;

  public DepartmentServiceImpl(DepartmentJpaRepository departmentJpaRepository) {
    this.departmentJpaRepository = departmentJpaRepository;
  }

  @Override
  public Department getDepartment(Integer id) {
    return departmentJpaRepository.findById(id)
            .orElseThrow(() -> {
              String msg = String.format("Department does not exists or cannot be found. ID: %d", id);
              return new DepartmentNotFoundException(msg);
            });
  }

  @Override
  public List<Department> getDepartments() {
    return departmentJpaRepository.findAll();
  }

  @Override
  public Department saveDepartment(DepartmentDto dto) {
    Department department = dto.toDepartment();
    return departmentJpaRepository.save(department);
  }

  @Override
  public Department updateDepartment(Integer id, DepartmentDto dto) {
    getDepartment(id);
    Department department = dto.toDepartment();
    department.setId(id);
    return departmentJpaRepository.save(department);
  }

  @Override
  public void deleteMany(DeleteIdsDto dto) {
    List<Department> departments = dto
            .getIds()
            .stream()
            .map(id -> Department.
                    builder()
                    .id(id)
                    .build())
            .collect(Collectors.toList());

    departmentJpaRepository.deleteAll(departments);
  }

  @Override
  public void deleteAllDepartment() {
    departmentJpaRepository.deleteAll();
  }

  @Override
  public boolean isDepartmentExists(Integer id) {
    return departmentJpaRepository.findById(id).isPresent();
  }
}
