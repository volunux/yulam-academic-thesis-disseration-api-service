package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyJpaRepository extends JpaRepository<Faculty, Integer> {
}
