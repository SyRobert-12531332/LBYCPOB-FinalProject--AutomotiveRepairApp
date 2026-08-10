package ph.dlsu.edu.lbycpob.model;

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

    // ---- Encapsulated, validated accessors ----

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        this.brand = brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model cannot be empty.");
        }
        this.model = model.trim();
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.isBlank()) {
            throw new IllegalArgumentException("Plate number cannot be empty.");
        }
        this.plateNumber = plateNumber.trim().toUpperCase();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }
        this.ownerName = ownerName.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.isBlank()) {
            throw new IllegalArgumentException("Contact number cannot be empty.");
        }
        this.contactNumber = contactNumber.trim();
    }

    /** Short display line used across the vehicle table and repair screens. */
    public String getSummary() {
        return "[" + plateNumber + "] " + brand + " " + model;
    }

    // ---- Factory method: encapsulates "which subclass do I build" in one place ----

    public static Vehicle create(String type, String brand, String model, String plateNumber,
                                 String ownerName, String contactNumber) {
        return switch (type) {
            case "Sedan" -> new Sedan(brand, model, plateNumber, ownerName, contactNumber);
            case "SUV" -> new SUV(brand, model, plateNumber, ownerName, contactNumber);
            case "Van" -> new Van(brand, model, plateNumber, ownerName, contactNumber);
            case "Pickup" -> new Pickup(brand, model, plateNumber, ownerName, contactNumber);
            case "Motorcycle" -> new Motorcycle(brand, model, plateNumber, ownerName, contactNumber);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }

    @Override
    public String toString() {
        return getVehicleType() + " " + getSummary();
    }
}