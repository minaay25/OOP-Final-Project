package model;

import java.util.ArrayList;
import java.util.List;
/**
 * CarInventory manages the collection of cars in the rental system.
 * Provides methods for adding, removing, and searching cars.
 * 
 * @author Mina
 * @version 1.0
 */
public class CarInventory {
    
    private List<Car> cars;
    private List<Rental> rentals;
    
    public CarInventory() {
        this.cars = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
    /**
     * Add a new car to inventory
     * @param car Car to be added
     * @return true if added successfully, false otherwise
     */
    public boolean addCar(Car car) {
        if (car != null && !cars.contains(car)) {
            cars.add(car);
            return true;
        }
        return false;
    }
    /**
     * Remove a car from inventory by ID
     * @param id Car ID to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeCar(String id) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).id.equals(id)) {
                cars.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     * Get all available cars
     * @return List of available cars
     */
    public List<Car> getAvailableCars() {
        List<Car> available = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                available.add(car);
            }
        }
        return available;
    }
    /**
     * Search cars by brand
     * @param brand Brand name to search
     * @return List of cars matching the brand
     */
    public List<Car> searchByBrand(String brand) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }
    /**
     * Search for electric cars
     * @return List of electric cars
     */
    public List<Car> searchElectricCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car instanceof ElectricCar) {
                result.add(car);
            }
        }
        return result;
    }
    /**
     * Search for gas cars
     * @return List of gas cars
     */
    public List<Car> searchGasCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car instanceof GasCar) {
                result.add(car);
            }
        }
        return result;
    }
    /**
     * Find car by ID
     * @param id Car ID
     * @return Car object if found, null otherwise
     */
    public Car findCarById(String id) {
        for (Car car : cars) {
            if (car.id.equals(id)) {
                return car;
            }
        }
        return null;
    }
    /**
     * Create a new rental
     * @param rental Rental object
     * @return true if rental created successfully
     */
    public boolean createRental(Rental rental) {
        if (rental != null && rental.getCar().isAvailable()) {
            rental.getCar().rent();
            rentals.add(rental);
            return true;
        }
        return false;
    }
    /**
     * Get all active rentals
     * @return List of active rentals
     */
    public List<Rental> getActiveRentals() {
        List<Rental> active = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.isActive()) {
                active.add(rental);
            }
        }
        return active;
    }
    /**
     * Get all cars in inventory
     * @return List of all cars
     */
    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }
    
    /**
     * Get total number of cars
     * @return Total car count
     */
    public int getTotalCars() {
        return cars.size();
    }
    
    public int getAvailableCarCount() {
        int count = 0;
        for (Car car : cars) {
            if (car.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    
    public void displayAllCars() {
        System.out.println("\n=== ALL CARS ===");
        for (Car car : cars) {
            String type = car instanceof ElectricCar ? "Electric" : "Gas";
            System.out.println(car.getBrand() + " (" + type + ") - Available: " + car.isAvailable());
        }
    }
    
    public void displayAvailableCars() {
        System.out.println("\n=== AVAILABLE CARS ===");
        for (Car car : getAvailableCars()) {
            System.out.println(car.getBrand());
        }
    }
}