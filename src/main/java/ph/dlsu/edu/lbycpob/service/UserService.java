package ph.dlsu.edu.lbycpob.service;

import repairapp.model.User;
import repairapp.util.JsonUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ABSTRACTION + ENCAPSULATION: hides both the on-disk file format and the
 * password hashing scheme behind the two verbs the UI actually needs:
 * register(...) and authenticate(...). No other class ever touches
 * Users.json or SHA-256 directly.
 */
public class UserService {

    private static final String FILE = "Users.json";

    public User register(String firstName, String lastName, String email, String rawPassword) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Please fill out all fields.");
        }
        List<Map<String, String>> rows = JsonUtil.readArrayOfObjects(FILE);
        Map<String, String> row = new LinkedHashMap<>();
        row.put("FirstName", firstName.trim());
        row.put("LastName", lastName.trim());
        row.put("Email", email.trim());
        row.put("Password", hash(rawPassword));
        rows.add(row);
        JsonUtil.writeArrayOfObjects(FILE, rows);
        return new User(firstName.trim(), lastName.trim(), email.trim(), row.get("Password"));
    }

    public Optional<User> authenticate(String fullName, String rawPassword) {
        List<Map<String, String>> rows = JsonUtil.readArrayOfObjects(FILE);
        String hashed = hash(rawPassword);
        for (Map<String, String> row : rows) {
            String candidateName = row.getOrDefault("FirstName", "") + " " + row.getOrDefault("LastName", "");
            if (candidateName.equals(fullName) && hashed.equals(row.get("Password"))) {
                return Optional.of(new User(row.get("FirstName"), row.get("LastName"),
                        row.get("Email"), row.get("Password")));
            }
        }
        return Optional.empty();
    }

    private String hash(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available on this JVM.", e);
        }
    }
}
