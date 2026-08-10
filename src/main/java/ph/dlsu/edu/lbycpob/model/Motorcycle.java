package ph.dlsu.edu.lbycpob.model;

import java.util.List;

/** INHERITANCE + POLYMORPHISM: Motorcycle-specific parts list. */
public class Motorcycle extends Vehicle {

    public Motorcycle(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        super(brand, model, plateNumber, ownerName, contactNumber);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public List<String> getDefaultParts() {
        return List.of("Engine", "Chain/Sprocket", "Brakes", "Tires", "Battery", "Forks");
    }
}