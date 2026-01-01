package model;
/**
 * Car is an abstract class representing a vehicle in the rental system.
 * Implements Rentable interface for rental operations.
 * This class demonstrates inheritance and polymorphism in OOP.
 * 
 * @author Mina
 * @version 1.0
 */
import interfacepkg.Rentable;

public abstract class Car implements Rentable {

    protected String id;
    protected String brand;
    protected double pricePerDay;
    protected boolean available;
    /**
     * Constructor to create a new Car
     * @param id Unique car identifier
     * @param brand Car brand/model
     * @param pricePerDay Daily rental price
     */
    public Car(String id, String brand, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }
    /**
     * Check if car is available for rent
     * @return true if available, false otherwise
     */
    @Override
    public boolean isAvailable() {
        return available;
    }
    /**
     * Rent the car, making it unavailable
     */
    public void rent() {
        available = false;
    }
    /**
     * Return the car, making it available again
     */
    public void returnCar() {
        available = true;
    }
    /**
     * Get the brand of the car
     * @return Car brand/model name
     */
    public String getBrand() {
        return brand;
    }

}
