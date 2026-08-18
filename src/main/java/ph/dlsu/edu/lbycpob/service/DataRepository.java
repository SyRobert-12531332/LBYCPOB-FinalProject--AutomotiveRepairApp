package ph.dlsu.edu.lbycpob.service;

import java.util.List;

/**
 * Shared contract for services that persist a List of records to a single
 * JSON file via JsonStore. Makes the "loadX() / saveX()" pattern already
 * followed by RepairService, VehicleService, and UserService an explicit,
 * checkable contract instead of an informal convention.
 *
 * PartsService and InstructionsService intentionally do NOT implement this
 * interface: they persist a Map (part -> catalog / part -> manual), not a
 * List, so a List-shaped contract would not fit what they actually store.
 *
 * @param <T> the record type stored in the JSON file (e.g. RepairJob, Vehicle, User)
 */
public interface DataRepository<T> {

    /** Loads every record from the backing JSON file. */
    List<T> loadAll();

    /** Overwrites the backing JSON file with the given records. */
    void saveAll(List<T> items);
}