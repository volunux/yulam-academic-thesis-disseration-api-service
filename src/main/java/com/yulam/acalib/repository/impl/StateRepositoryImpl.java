package com.yulam.acalib.repository.impl;

import com.yulam.acalib.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Slf4j
@Repository
public class StateRepositoryImpl implements StateRepository {

  private final EntityManager entityManager;

  public StateRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public boolean deleteById(Integer id) {
    Query query = entityManager.createQuery("delete from State where id = ?1");
    query.setParameter(1, id);
    long deleted = query.executeUpdate();

    return deleted > 0;
  }
}
