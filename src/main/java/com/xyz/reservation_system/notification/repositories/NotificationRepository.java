package com.xyz.reservation_system.notification.repositories;

import com.xyz.reservation_system.common.repositories.SequenceRepository;
import com.xyz.reservation_system.notification.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository
    extends JpaRepository<Notification, Integer>, SequenceRepository {}
