package com.xyz.reservation_system.reservation.scheduler.impl;

import com.xyz.reservation_system.notification.services.NotificationService;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.repositories.ReservationRepository;
import com.xyz.reservation_system.reservation.scheduler.ReservationReminderScheduler;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ReservationReminderSchedulerImpl implements ReservationReminderScheduler {
  private final ReservationRepository repository;
  private final NotificationService notificationService;

  public ReservationReminderSchedulerImpl(
      ReservationRepository repository, NotificationService notificationService) {
    this.repository = repository;
    this.notificationService = notificationService;
  }

  @Scheduled(cron = "0 */2 * * * *")
  @Override
  public void sendReminderToReservationForHoursBefore() {
    final ZonedDateTime now = ZonedDateTime.now();
    // TODO should store 4 in env to make it dynamic
    final ZonedDateTime fourHoursFromNow = now.plusHours(4);
    System.out.println("Scheduler running --");

    List<Reservation> fourHoursAheadReservations =
        repository.findByReservationDateBetween(now, fourHoursFromNow);
    if (!CollectionUtils.isEmpty(fourHoursAheadReservations)) {
      fourHoursAheadReservations.forEach(
          reservation -> {
            String message =
                String.format(
                    "This is a reminder for your reservation on %s with reservation ID: %s",
                    reservation.getReservationDate(), reservation.getReservationId());
            notificationService.createNotification(message, reservation);
            notificationService.sendNotification(message, reservation);
          });
    }
  }
}
