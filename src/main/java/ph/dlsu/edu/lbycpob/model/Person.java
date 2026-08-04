package ph.dlsu.edu.lbycpob.model;

/**
 * Abstraction: This class serves as an abstract blueprint for human entities.
 * It cannot be instantiated directly.
 */
public abstract class Person {
    // Encapsulation: All data variables are declared private to guard raw inputs
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Exposed safely via clean getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}