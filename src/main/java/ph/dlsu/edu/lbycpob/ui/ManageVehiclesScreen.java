package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

import static ph.dlsu.edu.lbycpob.util.UIUtil.pointer;

/**
 * Manage Vehicles page. Equivalent of CreateManageVehicles() in the original app.
 */
public class ManageVehiclesScreen {

    private final VBox view;
    private final TableView<Vehicle> vehicleTable = new TableView<>();

    private final ComboBox<String> typeInput = new ComboBox<>(
            FXCollections.observableArrayList("Select Type", "Sedan", "SUV", "Van", "Pickup", "Motorcycle"));
    private final TextField brandInput = new TextField();
    private final TextField modelInput = new TextField();
    private final TextField plateInput = new TextField();
    private final TextField ownerInput = new TextField();
    private final TextField contactInput = new TextField();

    public ManageVehiclesScreen(AutomationRepairApp app) {
        this.view = build(app);
    }

    public VBox getView() {
        return view;
    }

    public void refresh(List<Vehicle> vehicles) {
        vehicleTable.setItems(FXCollections.observableArrayList(vehicles));
    }

    public void clearInputs() {
        typeInput.getSelectionModel().selectFirst();
        brandInput.clear();
        modelInput.clear();
        plateInput.clear();
        ownerInput.clear();
        contactInput.clear();
    }

    private VBox build(AutomationRepairApp app) {
        VBox root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");
        root.getChildren().add(new TopNavBar(app, "Manage Vehicles", true).getRoot());

        setupTable();
        VBox.setVgrow(vehicleTable, Priority.ALWAYS);
        root.getChildren().add(vehicleTable);

        typeInput.getSelectionModel().selectFirst();
        brandInput.setPromptText("Brand (e.g., Honda)");
        modelInput.setPromptText("Model (e.g., Civic)");
        plateInput.setPromptText("Plate Number");
        ownerInput.setPromptText("Owner Name");
        contactInput.setPromptText("Contact Number");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, typeInput, brandInput, modelInput);
        grid.addRow(1, plateInput, ownerInput, contactInput);
        for (int i = 0; i < 3; i++) {
            javafx.scene.layout.ColumnConstraints cc = new javafx.scene.layout.ColumnConstraints();
            cc.setPercentWidth(100.0 / 3);
            grid.getColumnConstraints().add(cc);
        }
        typeInput.setMaxWidth(Double.MAX_VALUE);
        brandInput.setMaxWidth(Double.MAX_VALUE);
        modelInput.setMaxWidth(Double.MAX_VALUE);
        plateInput.setMaxWidth(Double.MAX_VALUE);
        ownerInput.setMaxWidth(Double.MAX_VALUE);
        contactInput.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(typeInput, Priority.ALWAYS);
        GridPane.setHgrow(brandInput, Priority.ALWAYS);
        GridPane.setHgrow(modelInput, Priority.ALWAYS);
        GridPane.setHgrow(plateInput, Priority.ALWAYS);
        GridPane.setHgrow(ownerInput, Priority.ALWAYS);
        GridPane.setHgrow(contactInput, Priority.ALWAYS);

        VBox.setMargin(grid, new Insets(15, 0, 15, 0));
        root.getChildren().add(grid);

        HBox buttonRow = new HBox(12);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);

        Button addButton = pointer(new Button("Add Vehicle"));
        addButton.getStyleClass().add("btn-success");
        addButton.setOnAction(e -> app.addVehicle(
                typeInput.getValue(),
                brandInput.getText().strip(),
                modelInput.getText().strip(),
                plateInput.getText().strip(),
                ownerInput.getText().strip(),
                contactInput.getText().strip()));

        Button deleteButton = pointer(new Button("Delete Selected"));
        deleteButton.getStyleClass().add("btn-danger");
        deleteButton.setOnAction(e -> app.deleteVehicles(
                List.copyOf(vehicleTable.getSelectionModel().getSelectedItems())));

        buttonRow.getChildren().addAll(addButton, deleteButton);
        root.getChildren().add(buttonRow);

        return root;
    }
}