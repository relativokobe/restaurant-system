package com.xyz.reservation_system.reservation.controllers.impl;

import com.xyz.reservation_system.reservation.controllers.ReservationController;
import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.UpdateReservationRequest;
import com.xyz.reservation_system.reservation.services.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationControllerImpl implements ReservationController {
  private final ReservationService reservationService;

  public ReservationControllerImpl(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping
  @Override
  public CompletableFuture<ReservationDTO> create(
      @RequestBody @Valid ReservationRequest request, HttpServletRequest httpServletRequest) {
    return reservationService.create(request, httpServletRequest.getHeader("user-id"));
  }

  @PutMapping("/{reservationId}")
  @Override
  public CompletableFuture<ReservationDTO> update(
      @RequestBody @Valid UpdateReservationRequest request,
      @PathVariable("reservationId") String reservationId,
      HttpServletRequest httpServletRequest) {
    return reservationService.update(
        request, reservationId, httpServletRequest.getHeader("user-id"));
  }

  @GetMapping
  @Override
  public CompletableFuture<List<ReservationDTO>> retrieveReservations(
      @RequestParam(value = "pastReservations", required = false) Boolean pastReservations,
      HttpServletRequest httpServletRequest) {
    return reservationService.retrieveReservations(
        pastReservations, httpServletRequest.getHeader("user-id"));
  }
}
