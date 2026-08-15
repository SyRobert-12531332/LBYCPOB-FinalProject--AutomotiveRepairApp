package ph.dlsu.edu.lbycpob.model;

/**
 * Represents a single logged repair task, equivalent to a "log_entry"
 * dictionary written to Repairs.json in the original Python app.
 */
public class RepairJob {

    private String plate;
    private String mechanic;
    private String timestamp;
    private String part;
    private String severity;
    private String description;
    private int progress;

    public RepairJob() {
    }

    public RepairJob(String plate, String mechanic, String timestamp, String part,
                     String severity, String description, int progress) {
        this.plate = plate;
        this.mechanic = mechanic;
        this.timestamp = timestamp;
        this.part = part;
        this.severity = severity;
        this.description = description;
        this.progress = progress;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    /** Numeric sort weight so urgent repairs float to the top of the tables. */
    public static int urgencyWeight(String severity) {
        if (severity == null) {
            return 3;
        }
        return switch (severity) {
            case "Urgent Repair" -> 0;
            case "Needs Repair" -> 1;
            case "Minor Damage" -> 2;
            default -> 3;
        };
    }
}
