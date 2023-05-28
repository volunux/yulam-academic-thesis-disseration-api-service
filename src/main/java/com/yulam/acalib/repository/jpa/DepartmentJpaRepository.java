package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaRepository extends JpaRepository<Department, Integer> {

}
