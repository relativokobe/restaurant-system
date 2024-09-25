package com.xyz.reservation_system.notification.services.impl;

import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.notification.enums.NotificationStatus;
import com.xyz.reservation_system.notification.mapper.NotificationMapper;
import com.xyz.reservation_system.notification.repositories.NotificationRepository;
import com.xyz.reservation_system.notification.services.NotificationService;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.enums.NotificationChannel;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
  private final NotificationRepository repository;
  private final NotificationMapper mapper;

  public NotificationServiceImpl(NotificationRepository repository, NotificationMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  /**
   * Method to create notification and save it to DB
   *
   * @param message message of the notification
   * @param reservation the reservation related to the notification
   */
  @Override
  public void createNotification(String message, Reservation reservation) {
    final Notification notification =
        mapper.convertToEntity(
            message,
            NotificationStatus.UNREAD,
            ZonedDateTime.now(),
            reservation,
            reservation.getCustomer());
    repository.save(notification);
  }

  /**
   * Async method to send notification
   *
   * @param message message of the notification
   * @param reservation related to the notification
   * @return
   */
  @Async
  @Override
  public CompletableFuture<Void> sendNotification(String message, Reservation reservation) {
    if (reservation.getNotificationChannel() == NotificationChannel.EMAIL) {
      System.out.println(
          "sending notification to email: "
              + reservation.getBookerEmail()
              + " with message "
              + message);
    } else {
      System.out.println(
          "sending notification to phone number: "
              + reservation.getBookerPhoneNumber()
              + " with message "
              + message);
    }

    return CompletableFuture.completedFuture(null);
  }
}
