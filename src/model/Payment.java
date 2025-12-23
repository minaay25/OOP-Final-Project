package model;

import java.time.LocalDateTime;

public class Payment {
    
    private String paymentId;
    private Rental rental;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private boolean isPaid;
    
    public Payment(String paymentId, Rental rental, String paymentMethod) {
        this.paymentId = paymentId;
        this.rental = rental;
        this.amount = rental.getTotalCost();
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();
        this.isPaid = false;
    }
    
    public void processPayment() {
        if (!isPaid) {
            this.isPaid = true;
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment already processed.");
        }
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public Rental getRental() {
        return rental;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    public boolean isPaid() {
        return isPaid;
    }
    
    @Override
    public String toString() {
        return "Payment ID: " + paymentId +
               "\nRental ID: " + rental.getRentalId() +
               "\nAmount: $" + String.format("%.2f", amount) +
               "\nMethod: " + paymentMethod +
               "\nDate: " + paymentDate +
               "\nStatus: " + (isPaid ? "Paid" : "Pending");
    }
}