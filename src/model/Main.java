package model;

import java.time.LocalDate;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  CAR RENTAL SYSTEM DEMO");
        System.out.println("========================================\n");
        
        CarInventory inventory = new CarInventory();
        
        ElectricCar tesla = new ElectricCar("E001", "Tesla Model 3", 50.0, 75.0);
        ElectricCar nissan = new ElectricCar("E002", "Nissan Leaf", 35.0, 40.0);
        
        GasCar toyota = new GasCar("G001", "Toyota Camry", 40.0, 8.5);
        GasCar honda = new GasCar("G002", "Honda Civic", 35.0, 7.2);
        
        inventory.addCar(tesla);
        inventory.addCar(nissan);
        inventory.addCar(toyota);
        inventory.addCar(honda);
        
        System.out.println("Cars added to inventory!");
        inventory.displayAllCars();
        
        Customer customer1 = new Customer("Ali Yilmaz", "C001");
        Customer customer2 = new Customer("Ayse Demir", "C002");
        
        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM DEMO");
        System.out.println("========================================");
        int days = 5;
        System.out.println("Rental fee for " + days + " days:");
        System.out.println("- Tesla (Electric): $" + tesla.calculateRentalFee(days));
        System.out.println("- Toyota (Gas): $" + toyota.calculateRentalFee(days));
        System.out.println("(Different calculations for different car types!)\n");
        
        System.out.println("========================================");
        System.out.println("  CREATING RENTALS");
        System.out.println("========================================");
        
        Rental rental1 = new Rental("R001", customer1, tesla, LocalDate.now(), 7);
        Rental rental2 = new Rental("R002", customer2, toyota, LocalDate.now(), 3);
        
        inventory.createRental(rental1);
        inventory.createRental(rental2);
        
        System.out.println("\nRental 1 created:");
        System.out.println(rental1);
        System.out.println("\nRental 2 created:");
        System.out.println(rental2);
        
        System.out.println("\n========================================");
        System.out.println("  PAYMENT PROCESSING");
        System.out.println("========================================");
        
        Payment payment1 = new Payment("P001", rental1, "Credit Card");
        Payment payment2 = new Payment("P002", rental2, "Cash");
        
        System.out.println("\n" + payment1);
        payment1.processPayment();
        
        System.out.println("\n" + payment2);
        payment2.processPayment();
        
        System.out.println("\n========================================");
        System.out.println("  AVAILABLE CARS AFTER RENTALS");
        System.out.println("========================================");
        inventory.displayAvailableCars();
        
        System.out.println("\n========================================");
        System.out.println("  SEARCH BY BRAND: Nissan");
        System.out.println("========================================");
        List<Car> nissanCars = inventory.searchByBrand("Nissan Leaf");
        for (Car car : nissanCars) {
            System.out.println("- " + car.getBrand() + " (Available: " + car.isAvailable() + ")");
        }
        
        System.out.println("\n========================================");
        System.out.println("  SEARCH BY TYPE: Electric Cars");
        System.out.println("========================================");
        List<Car> electricCars = inventory.searchElectricCars();
        for (Car car : electricCars) {
            ElectricCar eCar = (ElectricCar) car;
            System.out.println("- " + car.getBrand() + " | Battery: " + eCar.getBatteryCapacity() + " kWh");
        }
        
        System.out.println("\n========================================");
        System.out.println("  RETURNING A CAR");
        System.out.println("========================================");
        rental1.completeRental();
        System.out.println(customer1.getName() + " returned " + tesla.getBrand());
        System.out.println("Tesla is now available: " + tesla.isAvailable());
        
        System.out.println("\n========================================");
        System.out.println("  INVENTORY STATISTICS");
        System.out.println("========================================");
        System.out.println("Total Cars: " + inventory.getTotalCars());
        System.out.println("Available Cars: " + inventory.getAvailableCarCount());
        System.out.println("Active Rentals: " + inventory.getActiveRentals().size());
        
        System.out.println("\n========================================");
        System.out.println("  DEMO COMPLETED!");
        System.out.println("========================================");
    }
}