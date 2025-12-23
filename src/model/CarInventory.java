package model;

import java.util.ArrayList;
import java.util.List;

public class CarInventory {
    
    private List<Car> cars;
    private List<Rental> rentals;
    
    public CarInventory() {
        this.cars = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }
    
    public boolean addCar(Car car) {
        if (car != null && !cars.contains(car)) {
            cars.add(car);
            return true;
        }
        return false;
    }
    
    public boolean removeCar(String id) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).id.equals(id)) {
                cars.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public List<Car> getAvailableCars() {
        List<Car> available = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                available.add(car);
            }
        }
        return available;
    }
    
    public List<Car> searchByBrand(String brand) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                result.add(car);
            }
        }
        return result;
    }
    
    public List<Car> searchElectricCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car instanceof ElectricCar) {
                result.add(car);
            }
        }
        return result;
    }
    
    public List<Car> searchGasCars() {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car instanceof GasCar) {
                result.add(car);
            }
        }
        return result;
    }
    
    public Car findCarById(String id) {
        for (Car car : cars) {
            if (car.id.equals(id)) {
                return car;
            }
        }
        return null;
    }
    
    public boolean createRental(Rental rental) {
        if (rental != null && rental.getCar().isAvailable()) {
            rental.getCar().rent();
            rentals.add(rental);
            return true;
        }
        return false;
    }
    
    public List<Rental> getActiveRentals() {
        List<Rental> active = new ArrayList<>();
        for (Rental rental : rentals) {
            if (rental.isActive()) {
                active.add(rental);
            }
        }
        return active;
    }
    
    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }
    
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