package com.xyz.reservation_system.reservation.dto;

import com.xyz.reservation_system.customer.dto.CustomerBookerDTO;
import com.xyz.reservation_system.reservation.enums.NotificationChannel;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import java.time.ZonedDateTime;

public record ReservationDTO(
    String reservationId,
    int numberOfGuests,
    String bookerName,
    String bookerPhoneNumber,
    ZonedDateTime reservationDate,
    String bookerEmail,
    ReservationStatus status,
    NotificationChannel notificationChannel,
    CustomerBookerDTO bookerUserInfo,
    ZonedDateTime createdAt) {}
