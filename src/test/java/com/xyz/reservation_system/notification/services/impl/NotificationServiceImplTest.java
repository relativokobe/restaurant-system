package com.xyz.reservation_system.notification.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.notification.mapper.NotificationMapper;
import com.xyz.reservation_system.notification.repositories.NotificationRepository;
import com.xyz.reservation_system.reservation.entities.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NotificationServiceImplTest {
  NotificationServiceImpl notificationService;
  NotificationRepository repository;
  NotificationMapper mapper;

  @BeforeEach
  public void setUp() {
    repository = Mockito.mock(NotificationRepository.class);
    mapper = Mockito.mock(NotificationMapper.class);
    notificationService = new NotificationServiceImpl(repository, mapper);
  }

  @Test
  public void testCreateNotification() {
    Mockito.when(repository.save(any())).thenReturn(new Notification());
    notificationService.createNotification("message", new Reservation());

    Mockito.verify(repository, Mockito.times(1)).save(any());
  }
}
