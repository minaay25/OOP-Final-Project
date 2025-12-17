package model;

public class ElectricCar extends Car {

    private double batteryCapacity;

    public ElectricCar(String id, String brand, double pricePerDay, double batteryCapacity) {
        super(id, brand, pricePerDay);
        this.batteryCapacity = batteryCapacity;
    }
}
