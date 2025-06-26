package model;

import java.time.LocalDate;

public class Reservation {
    private final String reservationId;
    private final User user;
    private final Book book;
    private final LocalDate reservationDate;

    public Reservation(String reservationId, User user, Book book, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "ID='" + reservationId + '\'' +
                ", User='" + user.getFullName() + '\'' +
                ", Book='" + book.getTitle() + '\'' +
                ", Date=" + reservationDate +
                '}';
    }
}
