package MoviebookingApp;

import java.util.Scanner;

public class MovieBookingApp {
    private static final UserService userService = new UserService();
    private static final BookingService bookingService = new BookingService();
    private static final MovieService movieService = new MovieService();
    private static final ReviewService reviewService = new ReviewService();
    private static final ShowtimeService showtimeService = new ShowtimeService();
    private static final PaymentService paymentService = new PaymentService();
    private static final AdminService adminService = new AdminService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;
        int userId = -1;

        while (true) {
            System.out.println("\n===== Movie Booking System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Browse Movies");
            System.out.println("4. Check Seat Availability");
            System.out.println("5. Book Seats");
            System.out.println("6. Booking History");
            System.out.println("7. Leave Review");
            System.out.println("8. Admin: View Reviews");
            System.out.println("9. Exit");
            System.out.print("Your choice: ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input. Try again.");
                continue;
            }

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Membership: "); String mem = sc.nextLine();
                    int id = userService.registerUser(name, email, mem);
                    if (id != -1) { System.out.println("Registered. ID: " + id); loggedIn = true; userId = id;}
                }
                case 2 -> {
                    System.out.print("Email: ");
                    String email2 = sc.nextLine();
                    int id2 = userService.loginUser(email2);
                    if (id2 != -1) { System.out.println("Login successful."); loggedIn = true; userId = id2; }
                    else System.out.println("Login failed.");
                }
                case 3 -> {
                    System.out.print("Genre (or blank): "); String genre = sc.nextLine();
                    System.out.print("Min rating (or blank): ");
                    String r = sc.nextLine();
                    double minR = r.isEmpty() ? 0 : Double.parseDouble(r);
                    movieService.browseMovies(genre, null, minR);
                }
                case 4 -> {
                    System.out.print("Show ID: "); int sid = sc.nextInt();
                    System.out.print("Seats needed: "); int seats = sc.nextInt(); sc.nextLine();
                    boolean avail = showtimeService.checkAvailability(sid, seats);
                    System.out.println(avail ? "Seats available." : "Seats not available.");
                }
                case 5 -> {
                    if (!loggedIn) { System.out.println("Login first."); break; }
                    System.out.print("Show ID: "); int sid2 = sc.nextInt();
                    System.out.print("Seats: "); int s2 = sc.nextInt(); sc.nextLine();
                    if (showtimeService.checkAvailability(sid2, s2)) {
                        if (paymentService.processPayment(userId, s2*100)) {
                            if (bookingService.bookSeats(userId, sid2, s2))
                                System.out.println("Booking confirmed!");
                            else System.out.println("Booking failed.");
                        }
                    } else {
                        System.out.println("Seats not available.");
                    }
                }
                case 6 -> {
                    if (!loggedIn) { System.out.println("Login first."); break; }
                    bookingService.viewBookingHistory(userId);
                }
                case 7 -> {
                    if (!loggedIn) { System.out.println("Login first."); break; }
                    System.out.print("Movie ID: "); int mid = sc.nextInt();
                    System.out.print("Rating (0–10): "); double rt = sc.nextDouble(); sc.nextLine();
                    System.out.print("Comment: "); String cm = sc.nextLine();
                    reviewService.addReview(userId, mid, rt, cm);
                }
                case 8 -> adminService.listAllReviews();
                case 9 -> { System.out.println("Exiting. Goodbye!"); sc.close(); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}

