package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    
    private static CarInventory inventory = new CarInventory();
    private static Scanner scanner = new Scanner(System.in);
    private static int rentalCounter = 1;
    private static int paymentCounter = 1;
    
    public static void main(String[] args) {
        
        // Örnek arabalar ekle
        inventory.addCar(new ElectricCar("E001", "Tesla Model 3", 50.0, 75.0));
        inventory.addCar(new ElectricCar("E002", "Nissan Leaf", 35.0, 40.0));
        inventory.addCar(new GasCar("G001", "Toyota Camry", 40.0, 8.5));
        inventory.addCar(new GasCar("G002", "Honda Civic", 35.0, 7.2));
        
        System.out.println("========================================");
        System.out.println("  CAR RENTAL SYSTEM");
        System.out.println("========================================\n");
        
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    displayAllCars();
                    break;
                case 2:
                    displayAvailableCars();
                    break;
                case 3:
                    addCar();
                    break;
                case 4:
                    rentCar();
                    break;
                case 5:
                    returnCar();
                    break;
                case 6:
                    searchCars();
                    break;
                case 7:
                    displayStatistics();
                    break;
                case 8:
                    processPayment();
                    break;
                case 0:
                    System.out.println("\nThank you for using Car Rental System!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void printMenu() {
        System.out.println("\n========================================");
        System.out.println("           MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. Display All Cars");
        System.out.println("2. Display Available Cars");
        System.out.println("3. Add New Car");
        System.out.println("4. Rent a Car");
        System.out.println("5. Return a Car");
        System.out.println("6. Search Cars");
        System.out.println("7. Display Statistics");
        System.out.println("8. Process Payment");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
    
    private static int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return choice;
        } catch (Exception e) {
            scanner.nextLine(); // clear buffer
            return -1;
        }
    }
    
    private static void displayAllCars() {
        System.out.println("\n=== ALL CARS IN INVENTORY ===");
        List<Car> cars = inventory.getAllCars();
        
        if (cars.isEmpty()) {
            System.out.println("No cars in inventory.");
            return;
        }
        
        for (Car car : cars) {
            String type = car instanceof ElectricCar ? "Electric" : "Gas";
            String status = car.isAvailable() ? "Available" : "Rented";
            System.out.println("ID: " + car.id + " | " + car.getBrand() + 
                             " (" + type + ") | $" + car.pricePerDay + "/day | " + status);
            
            if (car instanceof ElectricCar) {
                ElectricCar eCar = (ElectricCar) car;
                System.out.println("  Battery: " + eCar.getBatteryCapacity() + " kWh");
            } else if (car instanceof GasCar) {
                GasCar gCar = (GasCar) car;
                System.out.println("  Fuel Consumption: " + gCar.getFuelConsumption() + " L/100km");
            }
        }
    }
    
    private static void displayAvailableCars() {
        System.out.println("\n=== AVAILABLE CARS ===");
        List<Car> available = inventory.getAvailableCars();
        
        if (available.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }
        
        for (Car car : available) {
            String type = car instanceof ElectricCar ? "Electric" : "Gas";
            System.out.println("ID: " + car.id + " | " + car.getBrand() + 
                             " (" + type + ") | $" + car.pricePerDay + "/day");
        }
    }
    
    private static void addCar() {
        System.out.println("\n=== ADD NEW CAR ===");
        System.out.print("Car Type (1-Electric / 2-Gas): ");
        int type = getChoice();
        
        System.out.print("Car ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        
        System.out.print("Price per day: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        if (type == 1) {
            System.out.print("Battery Capacity (kWh): ");
            double battery = scanner.nextDouble();
            scanner.nextLine();
            
            ElectricCar car = new ElectricCar(id, brand, price, battery);
            if (inventory.addCar(car)) {
                System.out.println("\nElectric car added successfully!");
            } else {
                System.out.println("\nFailed to add car!");
            }
        } else if (type == 2) {
            System.out.print("Fuel Consumption (L/100km): ");
            double fuel = scanner.nextDouble();
            scanner.nextLine();
            
            GasCar car = new GasCar(id, brand, price, fuel);
            if (inventory.addCar(car)) {
                System.out.println("\nGas car added successfully!");
            } else {
                System.out.println("\nFailed to add car!");
            }
        } else {
            System.out.println("\nInvalid car type!");
        }
    }
    
    private static void rentCar() {
        System.out.println("\n=== RENT A CAR ===");
        displayAvailableCars();
        
        if (inventory.getAvailableCars().isEmpty()) {
            return;
        }
        
        System.out.print("\nEnter Car ID: ");
        String carId = scanner.nextLine();
        
        Car car = inventory.findCarById(carId);
        
        if (car == null) {
            System.out.println("Car not found!");
            return;
        }
        
        if (!car.isAvailable()) {
            System.out.println("Car is not available!");
            return;
        }
        
        System.out.print("Customer Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Customer ID: ");
        String customerId = scanner.nextLine();
        
        System.out.print("Number of days: ");
        int days = scanner.nextInt();
        scanner.nextLine();
        
        Customer customer = new Customer(name, customerId);
        String rentalId = "R" + String.format("%03d", rentalCounter++);
        Rental rental = new Rental(rentalId, customer, car, LocalDate.now(), days);
        
        if (inventory.createRental(rental)) {
            System.out.println("\n=== RENTAL CREATED ===");
            System.out.println(rental);
            System.out.println("\nTotal Cost: $" + rental.getTotalCost());
        } else {
            System.out.println("\nFailed to create rental!");
        }
    }
    
    private static void returnCar() {
        System.out.println("\n=== RETURN A CAR ===");
        List<Rental> activeRentals = inventory.getActiveRentals();
        
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        
        System.out.println("Active Rentals:");
        for (Rental rental : activeRentals) {
            System.out.println("Rental ID: " + rental.getRentalId() + 
                             " | Car: " + rental.getCar().getBrand() + 
                             " | Customer: " + rental.getCustomer().getName());
        }
        
        System.out.print("\nEnter Rental ID to return: ");
        String rentalId = scanner.nextLine();
        
        for (Rental rental : activeRentals) {
            if (rental.getRentalId().equals(rentalId)) {
                rental.completeRental();
                System.out.println("\nCar returned successfully!");
                System.out.println(rental.getCar().getBrand() + " is now available.");
                return;
            }
        }
        
        System.out.println("Rental ID not found!");
    }
    
    private static void searchCars() {
        System.out.println("\n=== SEARCH CARS ===");
        System.out.println("1. Search by Brand");
        System.out.println("2. Search Electric Cars");
        System.out.println("3. Search Gas Cars");
        System.out.print("Enter choice: ");
        
        int choice = getChoice();
        
        switch (choice) {
            case 1:
                System.out.print("Enter brand: ");
                String brand = scanner.nextLine();
                List<Car> byBrand = inventory.searchByBrand(brand);
                
                if (byBrand.isEmpty()) {
                    System.out.println("No cars found.");
                } else {
                    System.out.println("\nFound " + byBrand.size() + " car(s):");
                    for (Car car : byBrand) {
                        System.out.println("- " + car.getBrand() + " (Available: " + car.isAvailable() + ")");
                    }
                }
                break;
                
            case 2:
                List<Car> electric = inventory.searchElectricCars();
                System.out.println("\nElectric Cars (" + electric.size() + "):");
                for (Car car : electric) {
                    ElectricCar eCar = (ElectricCar) car;
                    System.out.println("- " + car.getBrand() + " | Battery: " + 
                                     eCar.getBatteryCapacity() + " kWh | Available: " + car.isAvailable());
                }
                break;
                
            case 3:
                List<Car> gas = inventory.searchGasCars();
                System.out.println("\nGas Cars (" + gas.size() + "):");
                for (Car car : gas) {
                    GasCar gCar = (GasCar) car;
                    System.out.println("- " + car.getBrand() + " | Fuel: " + 
                                     gCar.getFuelConsumption() + " L/100km | Available: " + car.isAvailable());
                }
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void displayStatistics() {
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.println("Total Cars: " + inventory.getTotalCars());
        System.out.println("Available Cars: " + inventory.getAvailableCarCount());
        System.out.println("Rented Cars: " + (inventory.getTotalCars() - inventory.getAvailableCarCount()));
        System.out.println("Active Rentals: " + inventory.getActiveRentals().size());
        System.out.println("Electric Cars: " + inventory.searchElectricCars().size());
        System.out.println("Gas Cars: " + inventory.searchGasCars().size());
    }
    
    private static void processPayment() {
        System.out.println("\n=== PROCESS PAYMENT ===");
        List<Rental> activeRentals = inventory.getActiveRentals();
        
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals to pay.");
            return;
        }
        
        System.out.println("Active Rentals:");
        for (Rental rental : activeRentals) {
            System.out.println("Rental ID: " + rental.getRentalId() + 
                             " | Amount: $" + rental.getTotalCost() + 
                             " | Customer: " + rental.getCustomer().getName());
        }
        
        System.out.print("\nEnter Rental ID: ");
        String rentalId = scanner.nextLine();
        
        for (Rental rental : activeRentals) {
            if (rental.getRentalId().equals(rentalId)) {
                System.out.print("Payment Method (Credit Card / Cash / Debit): ");
                String method = scanner.nextLine();
                
                String paymentId = "P" + String.format("%03d", paymentCounter++);
                Payment payment = new Payment(paymentId, rental, method);
                
                System.out.println("\n" + payment);
                payment.processPayment();
                return;
            }
        }
        
        System.out.println("Rental ID not found!");
    }
}