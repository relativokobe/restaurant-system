package com.xyz.reservation_system.common.repositories;

/** interface used to generate the next value of the Primary key */
public interface SequenceRepository {
  Long getNextValMySequence(String sequenceName);
}
