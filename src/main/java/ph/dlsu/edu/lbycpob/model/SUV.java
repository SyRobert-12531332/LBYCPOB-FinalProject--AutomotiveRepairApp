package ph.dlsu.edu.lbycpob.model;

import java.util.List;

/**
 * INHERITANCE + POLYMORPHISM: another Vehicle subtype with its own set of
 * default parts, selected purely by which class was instantiated.
 */
public class SUV extends Vehicle {

    public SUV(String brand, String model, String plateNumber, String ownerName, String contactNumber) {
        super(brand, model, plateNumber, ownerName, contactNumber);
    }

    @Override
    public String getVehicleType() {
        return "SUV";
    }

    @Override
    public List<String> getDefaultParts() {
        return List.of("Engine", "4WD System", "Brakes", "Suspension", "Tires", "Battery", "Transfer Case");
    }
}