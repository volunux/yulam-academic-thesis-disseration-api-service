package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, Integer> {
}
