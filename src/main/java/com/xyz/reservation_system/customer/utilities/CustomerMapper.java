package com.xyz.reservation_system.customer.utilities;

import com.xyz.reservation_system.customer.dto.RegisterCustomerReqDTO;
import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.customer.enums.CustomerStatus;
import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Used for mapping Customer Entity
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
  Customer convertRequestToEntity(
      RegisterCustomerReqDTO request, CustomerStatus status, ZonedDateTime createdAt);
}
