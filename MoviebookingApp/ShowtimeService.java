package MoviebookingApp;

import java.sql.*;

public class ShowtimeService {
    public boolean checkAvailability(int showID, int seatsRequested) {
        String query = "{CALL CheckAvailability(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setInt(1, showID);
            stmt.setInt(2, seatsRequested);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.execute();
            int available = stmt.getInt(3);
            return available >= seatsRequested;
        } catch (SQLException e) {
            System.out.println("Error checking availability: " + e.getMessage());
            return false;
        }
    }
}
