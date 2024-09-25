package com.xyz.reservation_system.reservation.controllers;

import com.xyz.reservation_system.reservation.dto.ReservationDTO;
import com.xyz.reservation_system.reservation.dto.ReservationRequest;
import com.xyz.reservation_system.reservation.dto.UpdateReservationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Reservation API")
public interface ReservationController {
  @Operation(
      summary = "Create Reservation",
      description = "This API is called to create reservation")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created new Reservation",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ReservationDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid Params", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Request not Authorize",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content),
      })
  CompletableFuture<ReservationDTO> create(
      ReservationRequest request, HttpServletRequest httpServletRequest);

  @Operation(
      summary = "Update Reservation",
      description =
          "This API is called to update the reservation. Can be used by restaurant or customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created new Reservation",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ReservationDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid Params", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Request not Authorize",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content),
      })
  CompletableFuture<ReservationDTO> update(
      UpdateReservationRequest request,
      String reservationId,
      HttpServletRequest httpServletRequest);

  @Operation(
      summary = "View past, upcoming, or all reservations of the current customer",
      description = "This API is called to retrieve reservations of the current customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully created new Reservation",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ReservationDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid Params", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Request not Authorize",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content),
      })
  CompletableFuture<List<ReservationDTO>> retrieveReservations(
      Boolean pastReservations, HttpServletRequest httpServletRequest);
}
