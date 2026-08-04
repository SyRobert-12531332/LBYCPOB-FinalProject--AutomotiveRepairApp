package ph.dlsu.edu.lbycpob.model;

public abstract class Vehicle {

    private int wheelCount;
    private String vehicleClass;

    public Vehicle(String vehicleClass, int wheelCount) {
        this.vehicleClass = vehicleClass;
        this.wheelCount = wheelCount;
    }

    public String getVehicleClass() { return vehicleClass; }

    public void setVehicleClass(String vehicleClass) { this.vehicleClass = vehicleClass; }

    public int getWheelCount() { return wheelCount; }

    public void setWheelCount(int wheelCount) { this.wheelCount = wheelCount; }
}
