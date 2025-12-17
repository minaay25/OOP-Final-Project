package model;

import interfacepkg.Rentable;

public abstract class Car implements Rentable {

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

    @Override
    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnCar() {
        available = true;
    }
    
    public String getBrand() {
        return brand;
    }

}
