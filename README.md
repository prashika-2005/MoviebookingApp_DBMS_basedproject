# MoviebookingApp_DBMS_basedproject : 
 This is a Java-based console application that simulates a real-world movie ticket booking system. The system uses JDBC to connect with MySQL, supports stored procedures, and includes modules for user registration, login, movie browsing, seat checking, bookings, payments, reviews, and admin features.

 Features
 User Registration & Login

 Browse Movies by genre or rating

 Check Seat Availability using a stored procedure

 Book Seats with confirmation and mock payment
  
 View Booking History

 Leave Reviews for movies

 Admin Section to view all reviews

 Secure JDBC Connectivity

 Technologies Used
Technology	Description
Java	Core backend logic (OOP + JDBC)
MySQL	Database to store users, bookings, etc.
JDBC	To connect Java with MySQL
Eclipse / IntelliJ	IDE for building the application
Stored Procedure	Used to check seat availability

 Database Schema (MySQL)
You'll need the following tables:

sql
Copy
Edit
CREATE DATABASE miniproject;
USE miniproject;

CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100) UNIQUE,
    MembershipType VARCHAR(50)
);

CREATE TABLE Movies (
    MovieID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(100),
    Genre VARCHAR(50),
    ReleaseDate DATE,
    Director VARCHAR(100),
    Rating DOUBLE,
    Duration INT
);

CREATE TABLE Showtimes (
    ShowID INT AUTO_INCREMENT PRIMARY KEY,
    MovieID INT,
    ShowDate DATETIME,
    AvailableSeats INT,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
);

CREATE TABLE Bookings (
    BookingID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ShowID INT,
    SeatsBooked INT,
    BookingDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ShowID) REFERENCES Showtimes(ShowID)
);

CREATE TABLE Reviews (
    ReviewID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    MovieID INT,
    Rating DOUBLE,
    Comments TEXT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
);
 Stored Procedure for Seat Availability:

sql
Copy
Edit
DELIMITER //

CREATE PROCEDURE CheckAvailability(
    IN inputShowID INT,
    IN requestedSeats INT,
    OUT availableSeats INT
)
BEGIN
    SELECT AvailableSeats INTO availableSeats
    FROM Showtimes
    WHERE ShowID = inputShowID;
END //

DELIMITER ;
 Project Structure
css
Copy
Edit
moviebookingSystem/
├── AdminService.java
├── BookingService.java
├── DatabaseConnection.java
├── MovieBookingApp.java (main)
├── MovieService.java
├── PaymentService.java
├── ReviewService.java
├── ShowtimeService.java
└── UserService.java
