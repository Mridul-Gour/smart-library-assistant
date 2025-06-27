package model;

import java.sql.Timestamp;

public class ReservationEntity {
    private int reservationId;
    private int userId;
    private int bookId;
    private Timestamp reservedAt;
    private Timestamp expiresAt;
    private String status;

    // Constructors
    public ReservationEntity() {}

    public ReservationEntity(int userId, int bookId, Timestamp expiresAt) {
        this.userId = userId;
        this.bookId = bookId;
        this.expiresAt = expiresAt;
        this.status = "active";
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Timestamp getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(Timestamp reservedAt) {
        this.reservedAt = reservedAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
