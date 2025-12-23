package model;

import java.time.LocalDate;

public class Rental {
    
    private String rentalId;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private boolean isActive;
    
    public Rental(String rentalId, Customer customer, Car car, LocalDate startDate, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(days);
        this.totalCost = car.calculateRentalFee(days);
        this.isActive = true;
    }
    
    public void completeRental() {
        this.isActive = false;
        car.returnCar();
    }
    
    public int getRentalDays() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    public String getRentalId() {
        return rentalId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Car getCar() {
        return car;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    @Override
    public String toString() {
        return "Rental ID: " + rentalId + 
               "\nCustomer: " + customer.getName() +
               "\nCar: " + car.getBrand() +
               "\nStart Date: " + startDate +
               "\nEnd Date: " + endDate +
               "\nTotal Cost: $" + String.format("%.2f", totalCost) +
               "\nStatus: " + (isActive ? "Active" : "Completed");
    }
}