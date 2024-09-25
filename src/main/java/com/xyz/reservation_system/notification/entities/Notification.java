package com.xyz.reservation_system.notification.entities;

import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.notification.enums.NotificationStatus;
import com.xyz.reservation_system.notification.utilities.NotificationEntityListener;
import com.xyz.reservation_system.reservation.entities.Reservation;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@EntityListeners(NotificationEntityListener.class)
@Entity
@Table(name = "notification")
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "notification_id", unique = true, updatable = false, nullable = false)
  private String notificationId;

  @Column(name = "message", nullable = false, length = 1000)
  private String message;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private NotificationStatus status;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "reservation_id", referencedColumnName = "id")
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(String notificationId) {
    this.notificationId = notificationId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public NotificationStatus getStatus() {
    return status;
  }

  public void setStatus(NotificationStatus status) {
    this.status = status;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Reservation getReservation() {
    return reservation;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }
}
