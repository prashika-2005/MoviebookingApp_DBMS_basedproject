package MoviebookingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewService {

    // Method to add a review for a movie
    public void addReview(int userID, int movieID, double rating, String comments) {
        if (!doesUserExist(userID)) {
            System.out.println("User not found.");
            return;
        }

        String query = "INSERT INTO Reviews (UserID, MovieID, Rating, Comments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, movieID);
            stmt.setDouble(3, rating);
            stmt.setString(4, comments);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Review added successfully.");
            } else {
                System.out.println("❌ Failed to add review.");
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Error adding review: " + e.getMessage());
        }
    }

    // Helper method to check if a user exists in the database
    private boolean doesUserExist(int userID) {
        String query = "SELECT COUNT(*) FROM Users WHERE UserID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Error checking user existence: " + e.getMessage());
        }

        return false;
    }
}

