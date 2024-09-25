package com.xyz.reservation_system.customer.entities;

import com.xyz.reservation_system.customer.enums.CustomerStatus;
import com.xyz.reservation_system.customer.utilities.CustomerEntityListener;
import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.reservation.entities.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@EntityListeners(CustomerEntityListener.class)
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "customer_id", unique = true, updatable = false, nullable = false)
  private String customerId;

  @Size(min = 6)
  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Size(min = 8)
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private CustomerStatus status;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @Column(name = "phone_number", unique = true, nullable = false)
  private String phoneNumber;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Notification> notifications;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> reservations;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public @Size(min = 8) String getPassword() {
    return password;
  }

  public void setPassword(@Size(min = 8) String password) {
    this.password = password;
  }

  public CustomerStatus getStatus() {
    return status;
  }

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }
}
