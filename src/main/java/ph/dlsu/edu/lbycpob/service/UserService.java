package ph.dlsu.edu.lbycpob.service;

import ph.dlsu.edu.lbycpob.model.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles persistence and lookups for mechanic accounts (Users.json).
 */
public class UserService {

    private static final String FILE_NAME = "Users.json";
    private static final Type LIST_TYPE = new TypeToken<ArrayList<User>>() {
    }.getType();

    public List<User> loadUsers() {
        return JsonStore.load(FILE_NAME, LIST_TYPE, new ArrayList<>());
    }

    public void saveUsers(List<User> users) {
        JsonStore.save(FILE_NAME, users);
    }

    /** Adds a new user to the file, appending to whatever is already saved. */
    public void registerUser(User newUser) {
        List<User> users = loadUsers();
        users.add(newUser);
        saveUsers(users);
    }

    /**
     * Attempts to authenticate by full name + password, matching the
     * original AttemptLogin() logic exactly.
     */
    public Optional<User> authenticate(String fullName, String password) {
        if (!JsonStore.exists(FILE_NAME)) {
            return Optional.empty();
        }
        List<User> users = loadUsers();
        for (User u : users) {
            String candidateFullName = u.getFirstName() + " " + u.getLastName();
            if (candidateFullName.equals(fullName) && u.getPassword().equals(password)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}