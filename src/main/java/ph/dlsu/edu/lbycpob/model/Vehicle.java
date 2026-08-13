package com.autoworks.repair.model;

/**
 * Represents a customer vehicle on record, equivalent to a row in the
 * original app's VehicleTable / Vehicles.json.
 */
public class Vehicle {

    private String type;
    private String brand;
    private String model;
    private String plate;
    private String owner;
    private String contact;

    public Vehicle() {
    }

    public Vehicle(String type, String brand, String model, String plate, String owner, String contact) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.owner = owner;
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    /** Display text used in the "Select a Vehicle" list on the Log Repairs screen. */
    public String toListDisplay() {
        return "[" + plate + "] " + brand + " " + model;
    }
}
