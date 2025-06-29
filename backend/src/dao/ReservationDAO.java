package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ReservationEntity;
import config.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationDAO {
    private static final Logger logger = LoggerFactory.getLogger(ReservationDAO.class);

    // INSERT a new reservation
    public boolean addReservation(ReservationEntity res) {
        logger.debug("Preparing to add reservation: userId={}, bookId={}, expiresAt={}, status={}", res.getUserId(), res.getBookId(), res.getExpiresAt(), res.getStatus());
        String sql = "INSERT INTO reservations (user_id, book_id, expires_at, status) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            conn.setAutoCommit(true); // Important for Oracle CLI sessions
            logger.debug("Auto-commit set to true for connection.");
    
            stmt.setQueryTimeout(5);  // Timeout in 5 seconds if Oracle hangs
            logger.debug("Query timeout set to 5 seconds.");
    
            stmt.setInt(1, res.getUserId());
            stmt.setInt(2, res.getBookId());
            stmt.setTimestamp(3, res.getExpiresAt());
            stmt.setString(4, res.getStatus());
    
            int rowsInserted = stmt.executeUpdate();
            logger.info("Reservation insert executed, rowsInserted={}", rowsInserted);
            if (rowsInserted == 0) {
                logger.warn("No reservation was inserted for userId={}, bookId={}", res.getUserId(), res.getBookId());
            }
            return rowsInserted > 0;
    
        } catch (SQLTimeoutException e) {
            logger.warn("Timeout while adding reservation: userId={}, bookId={}", res.getUserId(), res.getBookId(), e);
            return false;
        } catch (SQLException e) {
            logger.error("SQL Error while adding reservation: userId={}, bookId={}", res.getUserId(), res.getBookId(), e);
            return false;
        }
    }

    // GET all reservations for a user
    public List<ReservationEntity> getReservationsByUserId(int userId) {
        logger.debug("Fetching reservations for userId={}", userId);
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        List<ReservationEntity> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ReservationEntity res = new ReservationEntity();
                res.setReservationId(rs.getInt("reservation_id"));
                res.setUserId(rs.getInt("user_id"));
                res.setBookId(rs.getInt("book_id"));
                res.setReservedAt(rs.getTimestamp("reserved_at"));
                res.setExpiresAt(rs.getTimestamp("expires_at"));
                res.setStatus(rs.getString("status"));

                list.add(res);
            }
            logger.info("Fetched {} reservations for userId={}", list.size(), userId);
            if (list.isEmpty()) {
                logger.warn("No reservations found for userId={}", userId);
            }
        } catch (SQLException e) {
            logger.error("Error fetching reservations for userId={}", userId, e);
        }
        return list;
    }

    // CANCEL (Soft delete) a reservation
    public boolean cancelReservation(int reservationId) {
        logger.debug("Attempting to cancel reservation with reservationId={}", reservationId);
        String sql = "UPDATE reservations SET status = 'cancelled' WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);
            int rowsUpdated = stmt.executeUpdate();
            logger.info("Rows updated (cancelled): {} for reservationId={}", rowsUpdated, reservationId);
            if (rowsUpdated == 0) {
                logger.warn("No reservation was cancelled for reservationId={}", reservationId);
            }
            return rowsUpdated > 0;

        } catch (SQLException e) {
            logger.error("Error cancelling reservation with reservationId={}", reservationId, e);
            return false;
        }
    }

    // UPDATE reservation expiry date
    public boolean updateReservationExpiry(int reservationId, Timestamp newExpiry) {
        logger.debug("Attempting to update reservation expiry: reservationId={}, newExpiry={}", reservationId, newExpiry);
        String sql = "UPDATE reservations SET expires_at = ? WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, newExpiry);
            stmt.setInt(2, reservationId);

            int rowsUpdated = stmt.executeUpdate();
            logger.info("Rows updated (expiry): {} for reservationId={}", rowsUpdated, reservationId);
            if (rowsUpdated == 0) {
                logger.warn("No reservation expiry was updated for reservationId={}", reservationId);
            }
            return rowsUpdated > 0;

        } catch (SQLException e) {
            logger.error("Error updating reservation expiry for reservationId={}", reservationId, e);
            return false;
        }
    }
}
