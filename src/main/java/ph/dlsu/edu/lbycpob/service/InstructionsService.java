package ph.dlsu.edu.lbycpob.service;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Handles persistence for the editable "standard operating procedure"
 * manuals keyed by part name (Instructions.json).
 */
public class InstructionsService {

    private static final String FILE_NAME = "Instructions.json";
    private static final Type MAP_TYPE = new TypeToken<LinkedHashMap<String, String>>() {
    }.getType();

    public Map<String, String> loadInstructions() {
        return JsonStore.load(FILE_NAME, MAP_TYPE, new LinkedHashMap<>());
    }

    public void saveInstructions(Map<String, String> instructions) {
        JsonStore.save(FILE_NAME, instructions);
    }
}