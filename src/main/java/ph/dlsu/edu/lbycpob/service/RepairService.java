package ph.dlsu.edu.lbycpob.service;

import ph.dlsu.edu.lbycpob.model.RepairJob;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Handles persistence for logged repair jobs (Repairs.json), including the
// urgency-based sorting used on the Dashboard and Manage Repairs screens.

public class RepairService {

    private static final String FILE_NAME = "Repairs.json";
    private static final Type LIST_TYPE = new TypeToken<ArrayList<RepairJob>>() {
    }.getType();

    public List<RepairJob> loadRepairs() {
        return JsonStore.load(FILE_NAME, LIST_TYPE, new ArrayList<>());
    }

    public void saveRepairs(List<RepairJob> repairs) {
        JsonStore.save(FILE_NAME, repairs);
    }

    public void addRepair(RepairJob job) {
        List<RepairJob> repairs = loadRepairs();
        repairs.add(job);
        saveRepairs(repairs);
    }

    // Loads repairs and sorts them so Urgent Repair jobs appear first
    public List<RepairJob> loadRepairsSortedByUrgency() {
        List<RepairJob> repairs = loadRepairs();
        repairs.sort(Comparator.comparingInt(r -> RepairJob.urgencyWeight(r.getSeverity())));
        return repairs;
    }

    // Removes every repair job whose plate is in the given list, then saves; used after a vehicle delete
    public void deleteRepairsForPlates(List<String> plates) {
        if (!JsonStore.exists(FILE_NAME)) {
            return;
        }
        List<RepairJob> repairs = loadRepairs();
        List<RepairJob> updated = new ArrayList<>();
        for (RepairJob rep : repairs) {
            if (!plates.contains(rep.getPlate())) {
                updated.add(rep);
            }
        }
        saveRepairs(updated);
    }
}