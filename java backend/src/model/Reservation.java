package model;

import java.time.LocalDate;

public record Reservation(String reservationId, User user, Book book, LocalDate reservationDate) {
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", user=" + user +
                ", book=" + book +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
