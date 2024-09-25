package com.xyz.reservation_system.reservation.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.repositories.CustomerRepository;
import com.xyz.reservation_system.notification.services.NotificationService;
import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.UpdateReservationRequest;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.enums.NotificationChannel;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import com.xyz.reservation_system.reservation.repositories.ReservationRepository;
import com.xyz.reservation_system.reservation.utilities.ReservationMapper;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ReservationServiceImplTest {
  ReservationServiceImpl reservationService;
  ReservationRepository repository;
  ReservationMapper mapper;
  CustomerRepository customerRepository;
  NotificationService notificationService;

  @BeforeEach
  public void setUp() {
    repository = Mockito.mock(ReservationRepository.class);
    mapper = Mockito.mock(ReservationMapper.class);
    customerRepository = Mockito.mock(CustomerRepository.class);
    notificationService = Mockito.mock(NotificationService.class);
    reservationService =
        new ReservationServiceImpl(repository, mapper, customerRepository, notificationService);
  }

  @Test
  public void testCreate() throws ExecutionException, InterruptedException {
    final Reservation reservation = new Reservation();
    Mockito.when(mapper.convertToEntityOnCreation(any(), any(), any())).thenReturn(reservation);
    Mockito.when(repository.save(any())).thenReturn(reservation);
    Mockito.when(customerRepository.findByCustomerId(any()))
        .thenReturn(Optional.of(new Customer()));
    Mockito.when(notificationService.sendNotification(any(), any())).thenReturn(null);
    Mockito.doNothing().when(notificationService).createNotification(any(), any());

    final ReservationRequest request =
        new ReservationRequest(
            1,
            "bokerName",
            "1234566",
            ZonedDateTime.now(),
            "asd@gmail.com",
            NotificationChannel.EMAIL);

    final ReservationDTO reservationDTO =
        new ReservationDTO(
            "reservationId",
            1,
            "bookerName",
            "bookerPhoneNumber",
            ZonedDateTime.now(),
            "bookerEmail",
            ReservationStatus.PENDING,
            NotificationChannel.EMAIL,
            null,
            ZonedDateTime.now());
    Mockito.when(mapper.convertToDto(any())).thenReturn(reservationDTO);
    final ReservationDTO reservationDtoRsp = reservationService.create(request, "currentId").get();
    assertEquals(reservationDTO, reservationDtoRsp);
  }

  @Test
  public void testUpdate() throws ExecutionException, InterruptedException {
    final Reservation reservation = new Reservation();
    Mockito.when(repository.findByReservationId(any())).thenReturn(Optional.of(reservation));

    Mockito.when(mapper.convertToEntityOnCreation(any(), any(), any())).thenReturn(reservation);
    Mockito.when(repository.save(any())).thenReturn(reservation);
    Mockito.when(customerRepository.findByCustomerId(any()))
        .thenReturn(Optional.of(new Customer()));
    Mockito.when(notificationService.sendNotification(any(), any())).thenReturn(null);
    Mockito.doNothing().when(notificationService).createNotification(any(), any());

    final UpdateReservationRequest request =
        new UpdateReservationRequest(
            1,
            "bokerName",
            "1234566",
            ZonedDateTime.now(),
            "asd@gmail.com",
            NotificationChannel.EMAIL,
            ReservationStatus.PENDING);

    final ReservationDTO reservationDTO =
        new ReservationDTO(
            "reservationId",
            1,
            "bookerName",
            "bookerPhoneNumber",
            ZonedDateTime.now(),
            "bookerEmail",
            ReservationStatus.PENDING,
            NotificationChannel.EMAIL,
            null,
            ZonedDateTime.now());
    Mockito.when(mapper.convertToDto(any())).thenReturn(reservationDTO);
    final ReservationDTO reservationDtoRsp =
        reservationService.update(request, "reservationId", "currentId").get();
    assertEquals(reservationDTO, reservationDtoRsp);
  }

  @Test
  public void testCustomerNotFound() {
    final Reservation reservation = new Reservation();
    Mockito.when(mapper.convertToEntityOnCreation(any(), any(), any())).thenReturn(reservation);
    Mockito.when(repository.save(any())).thenReturn(reservation);
    Mockito.when(customerRepository.findByCustomerId(any())).thenReturn(Optional.empty());

    final ReservationDTO reservationDTO =
        new ReservationDTO(
            "reservationId",
            1,
            "bookerName",
            "bookerPhoneNumber",
            ZonedDateTime.now(),
            "bookerEmail",
            ReservationStatus.PENDING,
            NotificationChannel.EMAIL,
            null,
            ZonedDateTime.now());
    Mockito.when(mapper.convertToDto(any())).thenReturn(reservationDTO);
  }

  @Test
  public void testRetrieveReservationsAll() throws ExecutionException, InterruptedException {
    Mockito.when(customerRepository.findByCustomerId(any()))
        .thenReturn(Optional.of(new Customer()));
    final List<ReservationDTO> reservationDTOList =
        reservationService.retrieveReservations(null, "currentUserId").get();
    assertEquals(reservationDTOList.size(), 0);
  }

  @Test
  public void testRetrieveReservationsPast() throws ExecutionException, InterruptedException {
    Mockito.when(customerRepository.findByCustomerId(any()))
        .thenReturn(Optional.of(new Customer()));
    List<Reservation> reservations = new ArrayList<>();
    reservations.add(new Reservation());
    Mockito.when(repository.findByReservationDateBefore(any())).thenReturn(reservations);
    final List<ReservationDTO> reservationDTOList =
        reservationService.retrieveReservations(true, "currentUserId").get();
    assertEquals(reservationDTOList.size(), 1);
  }

  @Test
  public void testRetrieveReservationsFuture() throws ExecutionException, InterruptedException {
    Mockito.when(customerRepository.findByCustomerId(any()))
        .thenReturn(Optional.of(new Customer()));
    List<Reservation> reservations = new ArrayList<>();
    reservations.add(new Reservation());
    Mockito.when(repository.findByReservationDateAfter(any())).thenReturn(reservations);
    final List<ReservationDTO> reservationDTOList =
        reservationService.retrieveReservations(false, "currentUserId").get();
    assertEquals(reservationDTOList.size(), 1);
  }
}
