package ph.dlsu.edu.lbycpob.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Small wrapper around Gson that mirrors the repeated
 * "if os.path.exists(...) / json.load / json.dump" pattern used
 * throughout the original Python program.
 */
public final class JsonStore {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private JsonStore() {
    }

    public static boolean exists(String fileName) {
        return Files.exists(Path.of(fileName));
    }

    /**
     * Reads and parses a JSON file. Returns {@code fallback} if the file does not
     * exist or contains invalid JSON, just like the original code's try/except
     * json.JSONDecodeError blocks.
     */
    public static <T> T load(String fileName, Type type, T fallback) {
        if (!exists(fileName)) {
            return fallback;
        }
        try (FileReader reader = new FileReader(fileName)) {
            T result = GSON.fromJson(reader, type);
            return result != null ? result : fallback;
        } catch (JsonSyntaxException | IOException e) {
            System.err.println("Error reading " + fileName + ": " + e.getMessage());
            return fallback;
        }
    }

    /** Serializes and writes an object to disk with indent=4-equivalent pretty printing. */
    public static void save(String fileName, Object data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error writing " + fileName + ": " + e.getMessage());
        }
    }
}