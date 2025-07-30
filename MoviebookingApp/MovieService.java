package MoviebookingApp;

import java.sql.*;

public class MovieService {
    public void browseMovies(String genre, String releaseDate, Double minRating) {
        StringBuilder query = new StringBuilder("SELECT * FROM Movies WHERE 1=1");
        if (genre != null && !genre.isEmpty()) query.append(" AND Genre = ?");
        if (releaseDate != null && !releaseDate.isEmpty()) query.append(" AND ReleaseDate = ?");
        if (minRating != null) query.append(" AND Rating >= ?");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            int index = 1;
            if (genre != null && !genre.isEmpty()) stmt.setString(index++, genre);
            if (releaseDate != null && !releaseDate.isEmpty()) stmt.setString(index++, releaseDate);
            if (minRating != null) stmt.setDouble(index++, minRating);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Movie: " + rs.getString("Title"));
                System.out.println("Genre: " + rs.getString("Genre"));
                System.out.println("Rating: " + rs.getDouble("Rating"));
                System.out.println("---------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayMovies(String genre, double minRating) {
        browseMovies(genre, null, minRating);
    }
}
