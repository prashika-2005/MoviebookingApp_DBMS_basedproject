package MoviebookingApp;
import java.sql.*;
public class BookingService {
    public boolean bookSeats(int userID, int showID, int seatsRequested) {
        String query = "INSERT INTO Bookings (UserID, ShowID, SeatsBooked) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, showID);
            stmt.setInt(3, seatsRequested);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error booking seats: " + e.getMessage());
            return false;
        }
    }

    public void viewBookingHistory(int userId) {
        String query = "SELECT * FROM Bookings WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("BookingID"));
                System.out.println("Show ID: " + rs.getInt("ShowID"));
                System.out.println("Seats: " + rs.getInt("SeatsBooked"));
                System.out.println("Date: " + rs.getTimestamp("BookingDate"));
                System.out.println("---------------");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing history: " + e.getMessage());
        }
    }
}
