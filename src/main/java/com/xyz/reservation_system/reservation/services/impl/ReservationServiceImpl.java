package com.xyz.reservation_system.reservation.services.impl;

import com.xyz.reservation_system.common.enums.ErrorCode;
import com.xyz.reservation_system.common.exceptions.NoRecordsFoundException;
import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.repositories.CustomerRepository;
import com.xyz.reservation_system.notification.services.NotificationService;
import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.UpdateReservationRequest;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import com.xyz.reservation_system.reservation.repositories.ReservationRepository;
import com.xyz.reservation_system.reservation.services.ReservationService;
import com.xyz.reservation_system.reservation.utilities.ReservationMapper;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class ReservationServiceImpl implements ReservationService {
  private final ReservationRepository repository;
  private final ReservationMapper mapper;
  private final CustomerRepository customerRepository;
  private final NotificationService notificationService;

  public ReservationServiceImpl(
      ReservationRepository repository,
      ReservationMapper mapper,
      CustomerRepository customerRepository,
      NotificationService notificationService) {
    this.repository = repository;
    this.mapper = mapper;
    this.customerRepository = customerRepository;
    this.notificationService = notificationService;
  }

  @Transactional
  @Async
  @Override
  public CompletableFuture<ReservationDTO> create(
      ReservationRequest request, String currentUserId) {
    final Reservation reservation =
        mapper.convertToEntityOnCreation(request, ReservationStatus.PENDING, ZonedDateTime.now());
    final Customer customer =
        customerRepository
            .findByCustomerId(currentUserId)
            .orElseThrow(() -> new NoRecordsFoundException(ErrorCode.CURRENT_CUSTOMER_NOT_FOUND));
    reservation.setCustomer(customer);

    sendNotification(
        reservation,
        "You have successfully created a reservation with reservation id: "
            + reservation.getReservationId());
    return CompletableFuture.completedFuture(mapper.convertToDto(repository.save(reservation)));
  }

  @Transactional
  @Async
  @Override
  public CompletableFuture<ReservationDTO> update(
      UpdateReservationRequest request, String reservationId, String currentUserId) {
    final Reservation reservation =
        repository
            .findByReservationId(reservationId)
            .orElseThrow(() -> new NoRecordsFoundException(ErrorCode.RESERVATION_NOT_FOUND));
    customerRepository
        .findByCustomerId(currentUserId)
        .orElseThrow(() -> new NoRecordsFoundException(ErrorCode.CURRENT_CUSTOMER_NOT_FOUND));

    // TODO assumption. Should implement user role to see if user is the restaurant or customer

    final boolean statusChanged = reservation.getStatus() != request.status();
    updateReservationFields(reservation, request);
    repository.save(reservation);
    if (statusChanged) {
      sendNotification(
          reservation,
          String.format(
              "Reservation status for reservation id: (%s) has been changed to %s",
              reservation.getReservationId(), reservation.getStatus()));
    }

    return CompletableFuture.completedFuture(mapper.convertToDto(reservation));
  }

  @Override
  public CompletableFuture<List<ReservationDTO>> retrieveReservations(
      Boolean pastReservations, String currentUserId) {
    final Customer customer =
        customerRepository
            .findByCustomerId(currentUserId)
            .orElseThrow(() -> new NoRecordsFoundException(ErrorCode.CURRENT_CUSTOMER_NOT_FOUND));

    return CompletableFuture.completedFuture(retrieveReservations(pastReservations, customer));
  }

  private List<ReservationDTO> retrieveReservations(Boolean pastReservations, Customer customer) {
    final List<ReservationDTO> reservationDTOS;
    if (pastReservations == null) {
      reservationDTOS =
          CollectionUtils.isEmpty(customer.getReservations())
              ? new ArrayList<>()
              : customer.getReservations().stream().map(mapper::convertToDto).toList();
    } else if (pastReservations) {
      reservationDTOS =
          repository.findByReservationDateBefore(ZonedDateTime.now()).stream()
              .map(mapper::convertToDto)
              .toList();
    } else {
      reservationDTOS =
          repository.findByReservationDateAfter(ZonedDateTime.now()).stream()
              .map(mapper::convertToDto)
              .toList();
    }

    return reservationDTOS;
  }

  public void sendNotification(Reservation reservation, String message) {
    notificationService.createNotification(message, reservation);
    notificationService.sendNotification(message, reservation);
  }

  private void updateReservationFields(Reservation reservation, UpdateReservationRequest request) {
    reservation.setReservationDate(request.reservationDate());
    reservation.setNumberOfGuests(request.numberOfGuests());
    reservation.setStatus(request.status());
    reservation.setNotificationChannel(request.notificationChannel());
    reservation.setBookerName(request.bookerName());
    reservation.setBookerPhoneNumber(request.bookerPhoneNumber());
    reservation.setBookerEmail(request.bookerEmail());
  }
}
