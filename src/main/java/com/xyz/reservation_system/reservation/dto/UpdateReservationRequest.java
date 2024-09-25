package com.xyz.reservation_system.reservation.dto;

import com.xyz.reservation_system.reservation.enums.NotificationChannel;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import jakarta.validation.constraints.*;
import java.time.ZonedDateTime;

public record UpdateReservationRequest(
    @Min(value = 1, message = "Number of guests must be at least 1") int numberOfGuests,
    @NotBlank(message = "bookerName is required") String bookerName,
    @NotBlank(message = "bookerPhoneNumber is required") String bookerPhoneNumber,
    @Future ZonedDateTime reservationDate,
    @NotBlank(message = "bookerEmail is required") @Email(message = "Must be a valid email")
        String bookerEmail,
    @NotNull NotificationChannel notificationChannel,
    @NotNull ReservationStatus status) {}
