package com.xyz.reservation_system.reservation.entities;

import com.xyz.reservation_system.customer.entities.Customer;
import com.xyz.reservation_system.notification.entities.Notification;
import com.xyz.reservation_system.reservation.enums.NotificationChannel;
import com.xyz.reservation_system.reservation.enums.ReservationStatus;
import com.xyz.reservation_system.reservation.utilities.ReservationEntityListener;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@EntityListeners(ReservationEntityListener.class)
@Table(name = "reservation")
@Entity
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "reservation_id", unique = true, updatable = false, nullable = false)
  private String reservationId;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @Column(name = "reservation_date")
  private ZonedDateTime reservationDate;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ReservationStatus status;

  @Column(name = "number_of_guests")
  private int numberOfGuests;

  @Column(name = "booker_name", nullable = false)
  private String bookerName;

  @Column(name = "booker_phone_number")
  private String bookerPhoneNumber;

  @Column(name = "booker_email")
  private String bookerEmail;

  @Column(name = "notification_channel", nullable = false)
  @Enumerated(EnumType.STRING)
  private NotificationChannel notificationChannel;

  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Notification> notification;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
  private Customer customer;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(ZonedDateTime reservationDate) {
    this.reservationDate = reservationDate;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }

  public int getNumberOfGuests() {
    return numberOfGuests;
  }

  public void setNumberOfGuests(int numberOfGuests) {
    this.numberOfGuests = numberOfGuests;
  }

  public String getBookerName() {
    return bookerName;
  }

  public void setBookerName(String bookerName) {
    this.bookerName = bookerName;
  }

  public String getBookerPhoneNumber() {
    return bookerPhoneNumber;
  }

  public void setBookerPhoneNumber(String bookerPhoneNumber) {
    this.bookerPhoneNumber = bookerPhoneNumber;
  }

  public String getBookerEmail() {
    return bookerEmail;
  }

  public void setBookerEmail(String bookerEmail) {
    this.bookerEmail = bookerEmail;
  }

  public NotificationChannel getNotificationChannel() {
    return notificationChannel;
  }

  public void setNotificationChannel(NotificationChannel notificationChannel) {
    this.notificationChannel = notificationChannel;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Notification> getNotification() {
    return notification;
  }

  public void setNotification(List<Notification> notification) {
    this.notification = notification;
  }
}
