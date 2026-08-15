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
}