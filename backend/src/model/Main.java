package model;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Create books
        Book textBook = new Book.TextBook("Data Structures", "Narasimha Karumanchi", "ISBN1234", "Computer Science");
        Book novel = new Book.Novel("The Alchemist", "Paulo Coelho", "ISBN5678", "Fiction");

        // Create users
        User student = new User.Student("Anjali", "STU101", "CSE", 2);
        User faculty = new User.Faculty("Dr. Ramesh", "FAC202", "Engineering");

        // Create reservations
        Reservation normalReservation = new Reservation.NormalReservation(textBook, student, LocalDate.now());
        Reservation urgentReservation = new Reservation.UrgentReservation(novel, faculty, LocalDate.now(), "Research requirement");

        // Print out the data
        System.out.println("=== Normal Reservation ===");
        printReservationDetails(normalReservation);

        System.out.println("\n=== Urgent Reservation ===");
        printReservationDetails(urgentReservation);
    }

    private static void printReservationDetails(Reservation reservation) {
        System.out.println("Book Title: " + reservation.getBook().getTitle());
        System.out.println("Book Author: " + reservation.getBook().getAuthor());
        System.out.println("User Name: " + reservation.getUser().getName());
        System.out.println("Reservation Date: " + reservation.getReservationDate());

        if (reservation instanceof Reservation.UrgentReservation urgent) {
            System.out.println("Urgency Reason: " + urgent.reason());
        }
    }
}
