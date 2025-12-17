package model;

public class GasCar extends Car {

    private double fuelConsumption;

    public GasCar(String id, String brand, double pricePerDay, double fuelConsumption) {
        super(id, brand, pricePerDay);
        this.fuelConsumption = fuelConsumption;
    }
}
