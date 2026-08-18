package ph.dlsu.edu.lbycpob.ui;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.model.RepairJob;
import ph.dlsu.edu.lbycpob.util.UIUtil;

import java.util.List;

// Main menu/dashboard

public class DashboardScreen extends AppScreen {

    private final TableView<RepairJob> repairsTable = new TableView<>();

    public DashboardScreen(AutomationRepairApp app) {
        super(app, "Dashboard", false);
    }

    public void refresh(List<RepairJob> repairs) {
        repairsTable.setItems(FXCollections.observableArrayList(repairs));
    }

    @Override
    protected void buildContent(VBox root) {
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
    }

    private Button makeTile(String emoji, String label, Runnable onClick) {
        Button tile = UIUtil.pointer(new Button(emoji + "\n\n" + label));
        tile.getStyleClass().add("dashboard-tile");
        tile.setEffect(UIUtil.createShadow());
        tile.setOnAction(e -> onClick.run());
        return tile;
    }
}