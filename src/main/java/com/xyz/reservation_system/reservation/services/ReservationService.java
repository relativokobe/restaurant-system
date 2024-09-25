package com.xyz.reservation_system.reservation.services;

import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.UpdateReservationRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReservationService {
  CompletableFuture<ReservationDTO> create(ReservationRequest request, String currentUserId);

  CompletableFuture<ReservationDTO> update(
      UpdateReservationRequest request, String reservationId, String currentUserId);

  CompletableFuture<List<ReservationDTO>> retrieveReservations(
      Boolean pastReservations, String currentUserId);
}
