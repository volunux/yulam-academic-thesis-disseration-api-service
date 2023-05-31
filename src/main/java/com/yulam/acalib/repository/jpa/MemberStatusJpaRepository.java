package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatusJpaRepository extends JpaRepository<MemberStatus, Integer> {
}
