package repairapp.model;

import java.util.List;

/**
 * Abstract base type for every vehicle serviced by the shop.
 *
 * ABSTRACTION: calling code works with the Vehicle contract
 * (getVehicleType, getDefaultParts, getSummary) and never needs to know
 * which concrete subclass it is actually holding.
 *
 * ENCAPSULATION: every field is private; the only way in or out is through
 * the getters/setters below, and the setters validate their input so a
 * Vehicle can never be left in a half-built, invalid state.
 */
public abstract class Vehicle {

    private String brand;
    private String model;
    private String plateNumber;
    private String ownerName;
    private String contactNumber;

    protected Vehicle(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        setBrand(brand);
        setModel(model);
        setPlateNumber(plateNumber);
        setOwnerName(ownerName);
        setContactNumber(contactNumber);
    }

    // ---- Abstraction + polymorphism hooks: every subclass answers these differently ----

    /** e.g. "Sedan", "SUV", "Motorcycle" - used for display and part lookups. */
    public abstract String getVehicleType();

    /** The parts a mechanic can typically service on this type of vehicle. */
    public abstract List<String> getDefaultParts();
