package ph.dlsu.edu.lbycpob.service;

import ph.dlsu.edu.lbycpob.model.Vehicle;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistence for customer vehicles (Vehicles.json).
 */
public class VehicleService {

    private static final String FILE_NAME = "Vehicles.json";
    private static final Type LIST_TYPE = new TypeToken<ArrayList<Vehicle>>() {
    }.getType();

    public List<Vehicle> loadVehicles() {
        return JsonStore.load(FILE_NAME, LIST_TYPE, new ArrayList<>());
    }

    public void saveVehicles(List<Vehicle> vehicles) {
        JsonStore.save(FILE_NAME, vehicles);
    }
}
