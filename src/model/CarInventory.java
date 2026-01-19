package model;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * CarInventory manages the collection of cars in the rental system.
 */
public class CarInventory {
    
    private List<Car> cars;
    private List<Rental> rentals;
    
    public CarInventory() {
        this.cars = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    // CSV'DEN ARAÇLARI YÜKLEYEN YENİ METOD
    public void loadCarsFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Başlık satırını atla
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                String id = data[0].trim();
                String type = data[1].trim();
                String brand = data[2].trim();
                double price = Double.parseDouble(data[3].trim());
                double extra = Double.parseDouble(data[4].trim());

                if (type.equalsIgnoreCase("Electric")) {
                    addCar(new ElectricCar(id, brand, price, extra));
                } else if (type.equalsIgnoreCase("Gas")) {
                    addCar(new GasCar(id, brand, price, extra));
                }
            }
            System.out.println(">>> Sistem: Araçlar CSV dosyasından başarıyla yüklendi.");
        } catch (IOException | NumberFormatException e) {
            System.out.println(">>> Hata: CSV okunurken hata oluştu: " + e.getMessage());
        }
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