package MoviebookingApp;
import java .sql.*;
public class UserService {
	public int registerUser(String name, String email, String membershipType) {
        String query = "INSERT INTO Users (Name, Email, MembershipType) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, membershipType);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
        return -1;
    }

    public int loginUser(String email) {
        String query = "SELECT UserID FROM Users WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getInt("UserID");
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return -1;
    }

}
