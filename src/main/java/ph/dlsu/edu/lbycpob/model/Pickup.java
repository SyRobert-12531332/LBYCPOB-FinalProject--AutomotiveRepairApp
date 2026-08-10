package ph.dlsu.edu.lbycpob.model;

import ph.dlsu.edu.lbycpob.model.Vehicle;

import java.util.List;

/** INHERITANCE + POLYMORPHISM: Pickup-specific parts list. */
public class Pickup extends Vehicle {

    public Pickup(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        super(brand, model, plateNumber, ownerName, contactNumber);
    }

    @Override
    public String getVehicleType() {
        return "Pickup";
    }

    @Override
    public List<String> getDefaultParts() {
        return List.of("Engine", "Transmission", "Tailgate", "Suspension", "Tires", "Battery", "Tow Hitch");
    }
}