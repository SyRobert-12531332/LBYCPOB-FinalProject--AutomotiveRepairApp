package com.autoworks.repair.ui;

import com.autoworks.repair.AutomationRepairApp;
import com.autoworks.repair.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

import static com.autoworks.repair.util.UIUtil.pointer;

/**
 * Log Repairs page: pick a vehicle on the left, fill out the repair
 * details on the right. Equivalent of CreateLogRepairs() in the original app.
 */
public class LogRepairsScreen {

    private final AutomationRepairApp app;
    private final VBox view;

    private final ListView<Vehicle> vehicleList = new ListView<>();
    private final Label mechanicLabel = new Label("Mechanic: Unknown");
    private final Label dateLabel = new Label("Date: --/--/----");
    private final Label selectedVehicleLabel = new Label("No Vehicle Selected");

    private final ComboBox<String> partDropdown = new ComboBox<>();
    private final TextField newPartInput = new TextField();
    private final Button addPartButton;
    private final ComboBox<String> severityDropdown = new ComboBox<>(
            FXCollections.observableArrayList("Needs Repair", "Urgent Repair", "Minor Damage"));
    private final TextArea descriptionArea = new TextArea();

    public LogRepairsScreen(AutomationRepairApp app) {
        this.app = app;
        this.addPartButton = pointer(new Button("Add Part"));
        this.view = build();
    }

    public VBox getView() {
        return view;
    }

    public void refreshVehicleList(List<Vehicle> vehicles) {
        vehicleList.setItems(FXCollections.observableArrayList(vehicles));
        selectedVehicleLabel.setText("No Vehicle Selected");
        partDropdown.setDisable(true);
        newPartInput.setDisable(true);
        addPartButton.setDisable(true);
        partDropdown.getItems().clear();
    }

    public void onVehicleSelected(Vehicle vehicle, String mechanicName, List<String> parts) {
        selectedVehicleLabel.setText(vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getPlate() + ")");
        mechanicLabel.setText("Mechanic: " + mechanicName);

        partDropdown.setItems(FXCollections.observableArrayList(parts));
        if (!parts.isEmpty()) {
            partDropdown.getSelectionModel().selectFirst();
        }
        partDropdown.setDisable(false);
        newPartInput.setDisable(false);
        addPartButton.setDisable(false);
    }

    public void addPartToDropdown(String newPart) {
        partDropdown.getItems().add(newPart);
        partDropdown.getSelectionModel().select(newPart);
    }

    public void clearNewPartInput() {
        newPartInput.clear();
    }

    public void clearForm() {
        descriptionArea.clear();
        severityDropdown.getSelectionModel().selectFirst();
    }

