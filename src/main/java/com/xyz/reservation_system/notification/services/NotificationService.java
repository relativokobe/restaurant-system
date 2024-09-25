package com.xyz.reservation_system.notification.services;

import com.xyz.reservation_system.reservation.entities.Reservation;
import java.util.concurrent.CompletableFuture;

public interface NotificationService {
  void createNotification(String message, Reservation reservation);

  CompletableFuture<Void> sendNotification(String message, Reservation reservation);
}
