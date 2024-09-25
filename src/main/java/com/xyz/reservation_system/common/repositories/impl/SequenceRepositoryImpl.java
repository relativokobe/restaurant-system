package com.xyz.reservation_system.common.repositories.impl;

import com.xyz.reservation_system.common.repositories.SequenceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SequenceRepositoryImpl implements SequenceRepository {
  @Autowired private EntityManagerFactory entityManagerFactory;

  /**
   * Get the next value of the primary key of the current entity
   *
   * @param sequenceName the key of the primary id of the entity
   * @return the next value of the primary ID
   */
  @Override
  public Long getNextValMySequence(String sequenceName) {
    try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
      return (Long)
          entityManager
              .createNativeQuery(
                  "SELECT last_value + i.inc - CASE WHEN is_called THEN 0 ELSE 1 END FROM "
                      + sequenceName
                      + ", (SELECT seqincrement AS inc FROM pg_sequence "
                      + "WHERE seqrelid = '"
                      + sequenceName
                      + "'::::regclass::::oid) "
                      + "AS i;")
              .getSingleResult();
    }
  }
}
