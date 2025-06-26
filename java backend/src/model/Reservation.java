package model;

import java.time.LocalDate;

public sealed interface Reservation permits Reservation.NormalReservation, Reservation.UrgentReservation {
    Book getBook();
    User getUser();
    LocalDate getReservationDate();

    record NormalReservation(Book book, User user, LocalDate reservationDate) implements Reservation {
        @Override
        public Book getBook() {
            return book;
        }

        @Override
        public User getUser() {
            return user;
        }

        @Override
        public LocalDate getReservationDate() {
            return reservationDate;
        }
    }

    record UrgentReservation(Book book, User user, LocalDate reservationDate, String reason) implements Reservation {
        @Override
        public Book getBook() {
            return book;
        }

        @Override
        public User getUser() {
            return user;
        }

        @Override
        public LocalDate getReservationDate() {
            return reservationDate;
        }
    }
}
