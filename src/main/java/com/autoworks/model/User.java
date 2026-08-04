package com.autoworks.model;
/**
 * ENCAPSULATION: passwordHash is never exposed as plain text once stored;
 * UserService is the only class allowed to compute or compare hashes.
 */
public class User {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String passwordHash;

    public User(String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return getFullName() + " <" + email + ">";
    }
}
