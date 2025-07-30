package MoviebookingApp;

public class PaymentService {
    public boolean processPayment(int userId, int amount) {
        System.out.println("Processing payment of ₹" + amount + " for User ID: " + userId);
        return true; // Simulate success
    }
}
