package model;

public abstract class Car {

    protected String id;
    protected String brand;
    protected double pricePerDay;
    protected boolean available;

    public Car(String id, String brand, double pricePerDay) {
        this.id = id;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnCar() {
        available = true;
    }
}
