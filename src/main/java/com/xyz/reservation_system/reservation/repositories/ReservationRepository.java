package com.xyz.reservation_system.reservation.repositories;

import com.xyz.reservation_system.common.repositories.SequenceRepository;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository
    extends JpaRepository<Reservation, Integer>, SequenceRepository {
  Optional<Reservation> findByReservationId(String reservationId);

  List<Reservation> findByReservationDateAfter(ZonedDateTime now);

  List<Reservation> findByReservationDateBefore(ZonedDateTime now);

  List<Reservation> findByStatusAndReservationDateBetween(
      ReservationStatus status, ZonedDateTime now, ZonedDateTime future);
}
