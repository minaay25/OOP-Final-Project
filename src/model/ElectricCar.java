package model;
/**
 * ElectricCar extends Car class to represent electric vehicles.
 * Demonstrates inheritance and polymorphism with eco-friendly discount.
 * 
 * @author Mina
 * @version 1.0
 */
public class ElectricCar extends Car {

    private double batteryCapacity;

    public ElectricCar(String id, String brand, double pricePerDay, double batteryCapacity) {
        super(id, brand, pricePerDay);
        this.batteryCapacity = batteryCapacity;
    }
    /**
     * Calculate rental fee with eco-friendly discount (20% off)
     * Demonstrates polymorphism - overrides parent method
     * @param days Number of rental days
     * @return Total rental cost with eco discount
     */
    @Override
    public double calculateRentalFee(int days) {
        return days * pricePerDay * 0.9;
    }
    
    public double getBatteryCapacity() {
        return batteryCapacity;
    }

}
