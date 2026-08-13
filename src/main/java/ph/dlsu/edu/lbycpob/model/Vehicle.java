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

