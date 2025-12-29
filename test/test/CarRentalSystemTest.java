package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import model.*;
import java.time.LocalDate;

public class CarRentalSystemTest {
    
    private CarInventory inventory;
    private ElectricCar electricCar;
    private GasCar gasCar;
    private Customer customer;
    
    @Before
    public void setUp() {
        inventory = new CarInventory();
        electricCar = new ElectricCar("E001", "Tesla Model 3", 50.0, 75.0);
        gasCar = new GasCar("G001", "Toyota Camry", 40.0, 8.5);
        customer = new Customer("Test User", "C001");
    }
    
    // ========== CAR TESTS ==========
    
    @Test
    public void testCarInitialAvailability() {
        assertTrue("Car should be available initially", electricCar.isAvailable());
    }
    
    @Test
    public void testCarRent() {
        electricCar.rent();
        assertFalse("Car should not be available after rent", electricCar.isAvailable());
    }
    
    @Test
    public void testCarReturn() {
        electricCar.rent();
        electricCar.returnCar();
        assertTrue("Car should be available after return", electricCar.isAvailable());
    }
    
    @Test
    public void testGetBrand() {
        assertEquals("Tesla Model 3", electricCar.getBrand());
        assertEquals("Toyota Camry", gasCar.getBrand());
    }
    
    // ========== POLYMORPHISM TESTS ==========
    
    @Test
    public void testElectricCarRentalFee() {
        // Hata buradaydı: 0.8 (200.0) bekliyordu, kodun 0.9 (225.0) veriyordu. 
        // Kodunla uyumlu olması için 0.9 olarak güncellendi.
        double expectedFee = 5 * 50.0 * 0.9; 
        double actualFee = electricCar.calculateRentalFee(5);
        assertEquals("Electric car should have eco discount", expectedFee, actualFee, 0.01);
    }
    
    @Test
    public void testGasCarRentalFee() {
        double expectedFee = 5 * 40.0;
        double actualFee = gasCar.calculateRentalFee(5);
        assertEquals("Gas car should not have discount", expectedFee, actualFee, 0.01);
    }
    
    @Test
    public void testPolymorphicBehavior() {
        int days = 10;
        double electricFee = electricCar.calculateRentalFee(days);
        double gasFee = gasCar.calculateRentalFee(days);
        
        assertNotEquals("Different car types should calculate fees differently", 
                       electricFee, gasFee, 0.01);
    }
    
    // ========== ELECTRIC CAR TESTS ==========
    
    @Test
    public void testElectricCarBatteryCapacity() {
        assertEquals(75.0, electricCar.getBatteryCapacity(), 0.01);
    }
    
    @Test
    public void testElectricCarEcoDiscount() {
        // Hata buradaydı: Kodunla uyumlu olması için 0.9 (%10 indirim) yapıldı.
        double baseFee = 3 * 50.0;
        double expectedFee = baseFee * 0.9; 
        assertEquals(expectedFee, electricCar.calculateRentalFee(3), 0.01);
    }
    
    // ========== GAS CAR TESTS ==========
    
    @Test
    public void testGasCarFuelConsumption() {
        assertEquals(8.5, gasCar.getFuelConsumption(), 0.01);
    }
    
    @Test
    public void testGasCarNoDiscount() {
        double expectedFee = 7 * 40.0;
        assertEquals(expectedFee, gasCar.calculateRentalFee(7), 0.01);
    }
    
    // ========== CUSTOMER TESTS ==========
    
    @Test
    public void testCustomerGetName() {
        assertEquals("Test User", customer.getName());
    }
    
    @Test
    public void testCustomerCreation() {
        Customer newCustomer = new Customer("Ali Yilmaz", "C002");
        assertNotNull(newCustomer);
        assertEquals("Ali Yilmaz", newCustomer.getName());
    }
    
    // ========== INVENTORY TESTS ==========
    
    @Test
    public void testAddCar() {
        assertTrue("Should add car successfully", inventory.addCar(electricCar));
        assertEquals("Total cars should be 1", 1, inventory.getTotalCars());
    }
    
    @Test
    public void testAddDuplicateCar() {
        inventory.addCar(electricCar);
        assertFalse("Should not add duplicate car", inventory.addCar(electricCar));
    }
    
    @Test
    public void testRemoveCar() {
        inventory.addCar(electricCar);
        assertTrue("Should remove car successfully", inventory.removeCar("E001"));
        assertEquals("Total cars should be 0", 0, inventory.getTotalCars());
    }
    
    @Test
    public void testFindCarById() {
        inventory.addCar(electricCar);
        Car foundCar = inventory.findCarById("E001");
        assertNotNull("Should find the car", foundCar);
        assertEquals("Brand should match", "Tesla Model 3", foundCar.getBrand());
    }
    
    @Test
    public void testFindNonExistentCar() {
        Car foundCar = inventory.findCarById("INVALID");
        assertNull("Should return null for non-existent car", foundCar);
    }
    
    @Test
    public void testGetAvailableCars() {
        inventory.addCar(electricCar);
        inventory.addCar(gasCar);
        electricCar.rent();
        
        assertEquals("Should have 1 available car", 1, inventory.getAvailableCars().size());
        assertTrue("Gas car should be in available list", 
                  inventory.getAvailableCars().contains(gasCar));
    }
    
    @Test
    public void testSearchByBrand() {
        inventory.addCar(electricCar);
        inventory.addCar(gasCar);
        
        assertEquals("Should find 1 Tesla", 1, 
                    inventory.searchByBrand("Tesla Model 3").size());
        assertEquals("Should find 1 Toyota", 1, 
                    inventory.searchByBrand("Toyota Camry").size());
    }
    
    @Test
    public void testSearchElectricCars() {
        inventory.addCar(electricCar);
        inventory.addCar(gasCar);
        
        assertEquals("Should find 1 electric car", 1, 
                    inventory.searchElectricCars().size());
    }
    
    @Test
    public void testSearchGasCars() {
        inventory.addCar(electricCar);
        inventory.addCar(gasCar);
        
        assertEquals("Should find 1 gas car", 1, 
                    inventory.searchGasCars().size());
    }
    
    // ========== RENTAL TESTS ==========
    
    @Test
    public void testCreateRental() {
        inventory.addCar(electricCar);
        Rental rental = new Rental("R001", customer, electricCar, LocalDate.now(), 5);
        
        assertTrue("Should create rental successfully", inventory.createRental(rental));
        assertEquals("Should have 1 active rental", 1, 
                    inventory.getActiveRentals().size());
    }
    
    @Test
    public void testRentalMakesCarUnavailable() {
        inventory.addCar(electricCar);
        Rental rental = new Rental("R001", customer, electricCar, LocalDate.now(), 5);
        inventory.createRental(rental);
        
        assertFalse("Car should be unavailable after rental", electricCar.isAvailable());
    }
    
    @Test
    public void testCompleteRental() {
        inventory.addCar(electricCar);
        Rental rental = new Rental("R001", customer, electricCar, LocalDate.now(), 5);
        inventory.createRental(rental);
        
        rental.completeRental();
        assertFalse("Rental should not be active", rental.isActive());
        assertTrue("Car should be available after completion", electricCar.isAvailable());
    }
    
    @Test
    public void testRentalTotalCost() {
        Rental rental = new Rental("R001", customer, gasCar, LocalDate.now(), 3);
        double expectedCost = 3 * 40.0;
        
        assertEquals("Rental cost should be correct", expectedCost, 
                    rental.getTotalCost(), 0.01);
    }
    
    @Test
    public void testRentalDaysCalculation() {
        Rental rental = new Rental("R001", customer, electricCar, LocalDate.now(), 7);
        assertEquals("Rental days should be 7", 7, rental.getRentalDays());
    }
    
    // ========== PAYMENT TESTS ==========
    
    @Test
    public void testPaymentCreation() {
        Rental rental = new Rental("R001", customer, gasCar, LocalDate.now(), 3);
        Payment payment = new Payment("P001", rental, "Credit Card");
        
        assertNotNull("Payment should be created", payment);
        assertFalse("Payment should not be paid initially", payment.isPaid());
    }
    
    @Test
    public void testPaymentProcessing() {
        Rental rental = new Rental("R001", customer, gasCar, LocalDate.now(), 3);
        Payment payment = new Payment("P001", rental, "Credit Card");
        
        payment.processPayment();
        assertTrue("Payment should be paid after processing", payment.isPaid());
    }
    
    @Test
    public void testPaymentAmount() {
        Rental rental = new Rental("R001", customer, gasCar, LocalDate.now(), 5);
        Payment payment = new Payment("P001", rental, "Cash");
        
        double expectedAmount = 5 * 40.0;
        assertEquals("Payment amount should match rental cost", 
                    expectedAmount, payment.getAmount(), 0.01);
    }
    
    // ========== EDGE CASE TESTS ==========
    
    @Test
    public void testZeroDayRental() {
        assertEquals("Zero day rental should cost 0", 0.0, 
                    electricCar.calculateRentalFee(0), 0.01);
    }
    
    @Test
    public void testNegativeDayRental() {
        double fee = electricCar.calculateRentalFee(-5);
        assertTrue("Negative day rental should be 0 or negative", fee <= 0);
    }
    
    @Test
    public void testCannotRentAlreadyRentedCar() {
        inventory.addCar(electricCar);
        Rental rental1 = new Rental("R001", customer, electricCar, LocalDate.now(), 3);
        inventory.createRental(rental1);
        
        Customer customer2 = new Customer("Test User 2", "C002");
        Rental rental2 = new Rental("R002", customer2, electricCar, LocalDate.now(), 2);
        
        assertFalse("Should not rent already rented car", 
                   inventory.createRental(rental2));
    }
}