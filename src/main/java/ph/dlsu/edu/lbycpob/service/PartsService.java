package ph.dlsu.edu.lbycpob.service;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles persistence for the per-vehicle-type parts catalog (Parts.json).
 * If no file exists yet, a sensible default catalog is created and saved,
 * mirroring LoadParts() in the original app.
 */
public class PartsService {

    private static final String FILE_NAME = "Parts.json";
    private static final Type MAP_TYPE = new TypeToken<LinkedHashMap<String, List<String>>>() {
    }.getType();

    public Map<String, List<String>> loadParts() {
        if (JsonStore.exists(FILE_NAME)) {
            Map<String, List<String>> loaded = JsonStore.load(FILE_NAME, MAP_TYPE, null);
            if (loaded != null) {
                return loaded;
            }
        }
        Map<String, List<String>> defaults = defaultParts();
        saveParts(defaults);
        return defaults;
    }

    public void saveParts(Map<String, List<String>> parts) {
        JsonStore.save(FILE_NAME, parts);
    }

    private Map<String, List<String>> defaultParts() {
        Map<String, List<String>> defaults = new LinkedHashMap<>();
        defaults.put("Sedan", List.of("Engine", "Transmission", "Brakes", "Suspension", "Tires", "Battery", "Alternator"));
        defaults.put("SUV", List.of("Engine", "4WD System", "Brakes", "Suspension", "Tires", "Battery", "Transfer Case"));
        defaults.put("Van", List.of("Engine", "Sliding Door Track", "Brakes", "Suspension", "Tires", "Battery", "Cooling System"));
        defaults.put("Pickup", List.of("Engine", "Transmission", "Tailgate", "Suspension", "Tires", "Battery", "Tow Hitch"));
        defaults.put("Motorcycle", List.of("Engine", "Chain/Sprocket", "Brakes", "Tires", "Battery", "Forks"));
        return defaults;
    }
}
