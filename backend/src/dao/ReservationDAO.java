package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ReservationEntity;
import config.DBConnection;

public class ReservationDAO {

    // INSERT a new reservation
    public boolean addReservation(ReservationEntity res) {
        String sql = "INSERT INTO reservations (user_id, book_id, expires_at, status) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            conn.setAutoCommit(true); // Important for Oracle CLI sessions
    
            stmt.setQueryTimeout(5);  // Timeout in 5 seconds if Oracle hangs
    
            System.out.println("Preparing to insert reservation: " + res.getUserId() + ", " + res.getBookId());
    
            stmt.setInt(1, res.getUserId());
            stmt.setInt(2, res.getBookId());
            stmt.setTimestamp(3, res.getExpiresAt());
            stmt.setString(4, res.getStatus());
    
            System.out.println("Executing query now...");
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
    
            return rowsInserted > 0;
    
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();  // Show full error
            return false;
        }
    }

    // GET all reservations for a user
    public List<ReservationEntity> getReservationsByUserId(int userId) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // CANCEL (Soft delete) a reservation
    public boolean cancelReservation(int reservationId) {
        String sql = "UPDATE reservations SET status = 'cancelled' WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);

            System.out.println("Cancelling reservation ID: " + reservationId);
            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows updated (cancelled): " + rowsUpdated);
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    // UPDATE reservation expiry date
    public boolean updateReservationExpiry(int reservationId, Timestamp newExpiry) {
        String sql = "UPDATE reservations SET expires_at = ? WHERE reservation_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, newExpiry);
            stmt.setInt(2, reservationId);

            System.out.println("Updating reservation ID: " + reservationId + " to new expiry: " + newExpiry);
            int rowsUpdated = stmt.executeUpdate();

            System.out.println("Rows updated (expiry): " + rowsUpdated);
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error updating reservation expiry: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
