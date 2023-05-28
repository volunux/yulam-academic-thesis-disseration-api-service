package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Integer> {

  Member findByEmailAddress(String emailAddress);
}
