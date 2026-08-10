package ph.dlsu.edu.lbycpob.model;

import java.util.List;

/**
 * INHERITANCE: reuses every field, constructor and getter from Vehicle.
 * POLYMORPHISM: overrides getVehicleType()/getDefaultParts() so code that
 * only knows about "a Vehicle" still gets Sedan-specific behavior at
 * runtime (dynamic dispatch).
 */
public class Sedan extends Vehicle {

    public Sedan(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        super(brand, model, plateNumber, ownerName, contactNumber);
    }

    @Override
    public String getVehicleType() {
        return "Sedan";
    }

    @Override
    public List<String> getDefaultParts() {
        return List.of("Engine", "Transmission", "Brakes", "Suspension", "Tires", "Battery", "Alternator");
    }
}