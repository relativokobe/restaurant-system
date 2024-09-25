package com.xyz.reservation_system.reservation.utilities;

import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.entities.Reservation;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {
  Reservation convertToEntityOnCreation(
      ReservationRequest request, ReservationStatus status, ZonedDateTime createdAt);

  @Mapping(target = "bookerUserInfo", source = "reservation.customer")
  ReservationDTO convertToDto(Reservation reservation);
}
