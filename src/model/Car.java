package model;

public abstract class Car {

    protected String id;
    protected String brand;
    protected double pricePerDay;

    public Car(String id, String brand, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
    }
}
