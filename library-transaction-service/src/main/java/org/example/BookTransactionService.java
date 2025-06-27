package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookTransactionService {
    private static final Logger logger = LoggerFactory.getLogger(BookTransactionService.class);

    // Reserve a book
    public void reserveBook(String userId, String bookId) {
        logger.info("Reservation started for userId={} and bookId={}", userId, bookId);

        try {
            if (bookId == null || bookId.isBlank()) {
                logger.warn("Book ID is null or blank. Cannot proceed with reservation.");
                return;
            }

            logger.debug("Checking availability for book ID: {}", bookId);
            boolean isAvailable = true;

            if (isAvailable) {
                logger.info("Book {} reserved successfully for user {}", bookId, userId);
                logger.debug("Reservation written to DB");
            } else {
                logger.warn("Book {} is not available for reservation", bookId);
            }

        } catch (Exception e) {
            logger.error("Error during reservation: {}", e.getMessage());
        }

        logger.info("Reservation process ended for userId={} and bookId={}", userId, bookId);
    }

    // Cancel a reservation
    public void cancelReservation(String userId, String bookId) {
        logger.info("Cancel request received for userId={} and bookId={}", userId, bookId);

        try {
            logger.debug("Removing reservation entry from DB...");
            // Simulate DB removal logic
            logger.info("Reservation for book {} by user {} successfully canceled.", bookId, userId);
        } catch (Exception e) {
            logger.error("Error while cancelling reservation: {}", e.getMessage());
        }
    }

    // Borrow a book
    public void borrowBook(String userId, String bookId) {
        logger.info("Borrowing started for userId={} and bookId={}", userId, bookId);

        try {
            logger.debug("Checking borrowing eligibility...");
            // Simulated check
            boolean canBorrow = true;

            if (canBorrow) {
                logger.info("Book {} successfully borrowed by user {}", bookId, userId);
            } else {
                logger.warn("User {} is not eligible to borrow book {}", userId, bookId);
            }

        } catch (Exception e) {
            logger.error("Borrow error: {}", e.getMessage());
        }
    }

    // Return a book
    public void returnBook(String userId, String bookId) {
        logger.info("Return initiated for userId={} and bookId={}", userId, bookId);

        try {
            logger.debug("Verifying return...");
            // Simulate return logic
            logger.info("Book {} returned successfully by user {}", bookId, userId);
        } catch (Exception e) {
            logger.error("Return failed: {}", e.getMessage());
        }
    }

    // Renew borrowed book
    public void renewBook(String userId, String bookId) {
        logger.info("Renewal requested for userId={} and bookId={}", userId, bookId);
        try {
            logger.debug("Checking if renewal allowed...");
            boolean canRenew = true;
            if (canRenew) {
                logger.info("Book renewed successfully for another period.");
            } else {
                logger.warn("Renewal not allowed for this book.");
            }
        } catch (Exception e) {
            logger.error("Renewal error: {}", e.getMessage());
        }
    }

    // Mark book as lost
    public void markBookAsLost(String userId, String bookId) {
        logger.info("Marking book as lost for userId={} and bookId={}", userId, bookId);
        try {
            logger.debug("Updating status to LOST...");
            logger.info("Book marked as lost. Fine may be applied.");
        } catch (Exception e) {
            logger.error("Error marking as lost: {}", e.getMessage());
        }
    }


    // MAIN METHOD TO ENABLE "RUN" BUTTON
    public static void main(String[] args) {
        BookTransactionService service = new BookTransactionService();

        service.reserveBook("U101", "B001");
        service.cancelReservation("U101", "B001");
        service.borrowBook("U102", "B002");
        service.returnBook("U102", "B002");
        service.reserveBook("U103", ""); // To trigger WARN
        service.renewBook("U103", "B003");
        service.markBookAsLost("U104", "B005");

    }

}
