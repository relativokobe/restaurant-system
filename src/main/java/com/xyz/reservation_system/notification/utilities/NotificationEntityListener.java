package com.xyz.reservation_system.notification.utilities;

import com.xyz.reservation_system.common.configs.ApplicationContextProvider;
import com.xyz.reservation_system.common.utilities.IdGenerator;
import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.notification.repositories.NotificationRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.PrePersist;

/**
 * Entity Listener that handles the generating of Notification ID before saving the customer to DB
 */
public class NotificationEntityListener {

  @PrePersist
  public void prePersist(Notification notification) {
    if (StringUtils.isEmpty(notification.getNotificationId())) {
      final NotificationRepository notificationRepository =
          (NotificationRepository)
              ApplicationContextProvider.getApplicationContext().getBean("notificationRepository");

      final String customerId =
          IdGenerator.generateIdWithPrefix(
              "N-", notificationRepository.getNextValMySequence("notification_id_seq"));
      notification.setNotificationId(customerId);
    }
  }
}
