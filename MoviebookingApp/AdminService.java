package MoviebookingApp;

import java.sql.*;

public class AdminService {
    public void listAllReviews() {
        String query = "SELECT * FROM Reviews";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("UserID: " + rs.getInt("UserID"));
                System.out.println("MovieID: " + rs.getInt("MovieID"));
                System.out.println("Rating: " + rs.getDouble("Rating"));
                System.out.println("Comments: " + rs.getString("Comments"));
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching reviews: " + e.getMessage());
        }
    }
}
