package com.xyz.reservation_system.notification.mapper;

import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.notification.enums.NotificationStatus;
import com.xyz.reservation_system.reservation.entities.Reservation;
import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", source = "status")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "reservation", source = "reservation")
  @Mapping(target = "customer", source = "customer")
  Notification convertToEntity(
      String message,
      NotificationStatus status,
      ZonedDateTime createdAt,
      Reservation reservation,
      Customer customer);
}
