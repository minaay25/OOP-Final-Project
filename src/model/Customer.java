package model;
/**
 * Customer class represents a person who rents cars from the system.
 * Stores customer information with encapsulation.
 * 
 * @author Mina
 * @version 1.0
 */
public class Customer {
	  /** Customer's full name */
    private String name;
    private String customerId;
    /**
     * Constructor to create a new Customer
     * @param name Customer's full name
     * @param customerId Unique customer ID
     */
    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }
}
