package com.yulam.acalib.repository.jpa;

import com.yulam.acalib.model.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StateJpaRepository extends JpaRepository<State, Integer> {

  Optional<State> findByTitle(String name);
}
