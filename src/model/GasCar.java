package model;
/**
 * GasCar extends Car class to represent gasoline-powered vehicles.
 * Demonstrates inheritance and polymorphism with standard pricing.
 * 
 * @author Mina
 * @version 1.0
 */
public class GasCar extends Car {

    private double fuelConsumption;
    /**
     * Constructor to create a new GasCar
     * @param id Unique car identifier
     * @param brand Car brand/model
     * @param pricePerDay Daily rental price
     * @param fuelConsumption Fuel consumption in L/100km
     */
    public GasCar(String id, String brand, double pricePerDay, double fuelConsumption) {
        super(id, brand, pricePerDay);
        this.fuelConsumption = fuelConsumption;
    }
    /**
     * Calculate rental fee with standard pricing
     * Demonstrates polymorphism - overrides parent method
     * @param days Number of rental days
     * @return Total rental cost
     */
    @Override
    public double calculateRentalFee(int days) {
        return days * pricePerDay;
    }
    
    public double getFuelConsumption() {
        return fuelConsumption;
    }

}
