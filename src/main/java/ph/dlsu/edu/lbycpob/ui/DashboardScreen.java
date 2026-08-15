package ph.dlsu.edu.lbycpob.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.model.RepairJob;
import ph.dlsu.edu.lbycpob.util.UIUtil;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;


// Main menu/dashboard

public class DashboardScreen {

    private final VBox view;
    private final TableView<RepairJob> repairsTable = new TableView<>();

    public DashboardScreen(AutomationRepairApp app) {
        this.view = build(app);
    }

    public VBox getView() {
        return view;
    }

    public void refresh(List<RepairJob> repairs) {
        repairsTable.setItems(FXCollections.observableArrayList(repairs));
    }

    private VBox build(AutomationRepairApp app) {
        VBox root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");

        root.getChildren().add(new TopNavBar(app, "Dashboard", false).getRoot());

        HBox tiles = new HBox(30);
        tiles.setAlignment(Pos.CENTER);
        tiles.setPadding(new Insets(0, 0, 30, 0));

        tiles.getChildren().add(makeTile("\uD83D\uDE97", "Manage Vehicles", app::openManageVehicles));
        tiles.getChildren().add(makeTile("\uD83D\uDD27", "Log Repairs", app::openLogRepairs));
        tiles.getChildren().add(makeTile("\uD83D\uDCCB", "Manage Repairs", app::openManageRepairsQueue));

        root.getChildren().add(tiles);

        Region divider = new Region();
        divider.setStyle("-fx-background-color: #e2e8f0;");
        divider.setPrefHeight(2);
        divider.setMaxHeight(2);
        VBox.setMargin(divider, new Insets(0, 0, 20, 0));
        root.getChildren().add(divider);

        Label tableTitle = new Label("Active Repairs Overview");
        tableTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        tableTitle.setStyle("-fx-text-fill: #0f172a;");
        VBox.setMargin(tableTitle, new Insets(0, 0, 10, 0));
        root.getChildren().add(tableTitle);

        setupTable();
        repairsTable.setEffect(UIUtil.createShadow());
        VBox.setVgrow(repairsTable, Priority.ALWAYS);
        root.getChildren().add(repairsTable);

        return root;
    }

    private Button makeTile(String emoji, String label, Runnable onClick) {
        Button tile = UIUtil.pointer(new Button(emoji + "\n\n" + label));
        tile.getStyleClass().add("dashboard-tile");
        tile.setEffect(UIUtil.createShadow());
        tile.setOnAction(e -> onClick.run());
        return tile;
    }

    private void setupTable() {
        repairsTable.getStyleClass().add("data-table");
        repairsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RepairJob, String> urgency = new TableColumn<>("Urgency");
        urgency.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getSeverity()));
        urgency.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    getStyleClass().remove("severity-urgent");
                } else {
                    setText(item);
                    if ("Urgent Repair".equals(item)) {
                        if (!getStyleClass().contains("severity-urgent")) {
                            getStyleClass().add("severity-urgent");
                        }
                    } else {
                        getStyleClass().remove("severity-urgent");
                    }
                }
            }
        });

        TableColumn<RepairJob, String> plate = new TableColumn<>("Vehicle Plate");
        plate.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPlate()));

        TableColumn<RepairJob, String> part = new TableColumn<>("Part");
        part.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPart()));

        TableColumn<RepairJob, String> mechanic = new TableColumn<>("Mechanic");
        mechanic.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMechanic()));

        TableColumn<RepairJob, String> progress = new TableColumn<>("Progress");
        progress.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getProgress() + "%"));

        repairsTable.getColumns().addAll(List.of(urgency, plate, part, mechanic, progress));
    }
}
