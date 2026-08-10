package ph.dlsu.edu.lbycpob.model;

import java.util.List;

/** INHERITANCE + POLYMORPHISM: Van-specific parts list. */
public class Van extends repairapp.model.Vehicle {

    public Van(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        super(brand, model, plateNumber, ownerName, contactNumber);
    }

    @Override
    public String getVehicleType() {
        return "Van";
    }

    @Override
    public List<String> getDefaultParts() {
        return List.of("Engine", "Sliding Door Track", "Brakes", "Suspension", "Tires", "Battery", "Cooling System");
    }
}