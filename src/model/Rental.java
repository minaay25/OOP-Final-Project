package model;

import java.time.LocalDate;

/**
 * Rental class represents a car rental transaction.
 * Manages rental details including dates, customer, and car information.
 * 
 * @author Mina
 * @version 1.0
 */
public class Rental {
	/** Unique rental identifier */
    private String rentalId;
    /** Customer who is renting the car */
    private Customer customer;
    /** Car being rented */
    private Car car;
    /** Start date of the rental */
    private LocalDate startDate;
    /** End date of the rental */
    private LocalDate endDate;
    /** Total cost of the rental */
    private double totalCost;
    /** Rental active status */
    private boolean isActive;
    /**
     * Constructor to create a new rental
     * @param rentalId Unique rental identifier
     * @param customer Customer renting the car
     * @param car Car being rented
     * @param startDate Rental start date
     * @param days Number of days for rental
     */
    public Rental(String rentalId, Customer customer, Car car, LocalDate startDate, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(days);
        this.totalCost = car.calculateRentalFee(days);
        this.isActive = true;
    }
    /**
     * Complete the rental and return the car
     */
    public void completeRental() {
        this.isActive = false;
        car.returnCar();
    }
    /**
     * Get rental duration in days
     * @return Number of rental days
     */
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