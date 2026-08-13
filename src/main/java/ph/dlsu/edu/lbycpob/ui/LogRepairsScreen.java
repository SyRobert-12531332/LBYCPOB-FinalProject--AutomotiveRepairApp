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

    public void updateClockLabel(String text) {
        dateLabel.setText(text);
    }

    private VBox build() {
        VBox root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");
        root.getChildren().add(new TopNavBar(app, "Log Repairs", true).getRoot());

        HBox content = new HBox(20);

        // --- Left panel: vehicle list ---
        VBox leftPanel = new VBox(10);
        Label listLabel = new Label("Select a Vehicle:");
        listLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        listLabel.setStyle("-fx-text-fill: #0f172a;");

        vehicleList.getStyleClass().add("data-list");
        vehicleList.setPrefWidth(300);
        vehicleList.setPrefHeight(560);
        vehicleList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);
                setText(empty || vehicle == null ? null : vehicle.toListDisplay());
            }
        });
        vehicleList.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                app.onRepairVehicleSelected(newV);
            }
        });

        leftPanel.getChildren().addAll(listLabel, vehicleList);

        // --- Right panel: repair form ---
        VBox rightPanel = new VBox(15);
        rightPanel.setPadding(new Insets(0, 0, 0, 20));
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        HBox metaRow = new HBox();
        mechanicLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));
        mechanicLabel.getStyleClass().add("subtle-label");
        dateLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));
        dateLabel.getStyleClass().add("subtle-label");
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        metaRow.getChildren().addAll(mechanicLabel, spacer, dateLabel);

        selectedVehicleLabel.getStyleClass().add("highlight-label");

        Label affectedPartLabel = sectionLabel("Affected Part:");
        partDropdown.setDisable(true);
        partDropdown.setMaxWidth(Double.MAX_VALUE);

        HBox addPartRow = new HBox(10);
        newPartInput.setPromptText("Or type a new part here...");
        newPartInput.setDisable(true);
        HBox.setHgrow(newPartInput, Priority.ALWAYS);
        addPartButton.getStyleClass().add("btn-accent");
        addPartButton.setDisable(true);
        addPartButton.setOnAction(e -> app.addNewPart(newPartInput.getText().strip()));
        addPartRow.getChildren().addAll(newPartInput, addPartButton);

        Label severityLabel = sectionLabel("Damage Severity:");
        severityDropdown.getSelectionModel().selectFirst();
        severityDropdown.setMaxWidth(Double.MAX_VALUE);

        Label descriptionLabel = sectionLabel("Damage Description:");
        descriptionArea.setPromptText("Additional Notes");
        descriptionArea.setPrefHeight(160);
        VBox.setVgrow(descriptionArea, Priority.ALWAYS);

        HBox submitRow = new HBox();
        submitRow.setAlignment(Pos.CENTER_RIGHT);
        Button submitButton = pointer(new Button("Save Repair Log"));
        submitButton.getStyleClass().add("btn-success");
        submitButton.setOnAction(e -> app.saveRepairLog(
                vehicleList.getSelectionModel().getSelectedItem(),
                partDropdown.getValue(),
                severityDropdown.getValue(),
                descriptionArea.getText()));
        submitRow.getChildren().add(submitButton);

