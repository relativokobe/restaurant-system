package com.xyz.reservation_system.reservation.utilities;

import com.xyz.reservation_system.common.configs.ApplicationContextProvider;
import com.xyz.reservation_system.common.utilities.IdGenerator;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.repositories.ReservationRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PrePersist;

/**
 * Entity Listener that handles the generating of reservation ID before saving the customer to DB
 */
public class ReservationEntityListener {
  @PrePersist
  public void prePersist(Reservation reservation) {
    if (StringUtils.isEmpty(reservation.getReservationId())) {
      final ReservationRepository reservationRepository =
          (ReservationRepository)
              ApplicationContextProvider.getApplicationContext().getBean("reservationRepository");

      final String reservationId =
          IdGenerator.generateIdWithPrefix(
              "R-", reservationRepository.getNextValMySequence("reservation_id_seq"));
      reservation.setReservationId(reservationId);
    }
  }
}
