package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.model.RepairJob;
import ph.dlsu.edu.lbycpob.util.UIUtil;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

import static ph.dlsu.edu.lbycpob.util.UIUtil.pointer;

// Manage Repairs page: lists every logged repair job, sorted by urgency,
// so a mechanic can open one to view/edit its manual and progress.
//  Equivalent of CreateManageRepairs() in the original app.

public class ManageRepairsScreen {

    private final VBox view;
    private final TableView<RepairJob> repairsTable = new TableView<>();

    public ManageRepairsScreen(AutomationRepairApp app) {
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
        root.getChildren().add(new TopNavBar(app, "Manage Repairs", true).getRoot());

        setupTable();
        repairsTable.setEffect(UIUtil.createShadow());
        VBox.setVgrow(repairsTable, Priority.ALWAYS);
        root.getChildren().add(repairsTable);

        Button openTaskButton = pointer(new Button("Open Selected Repair Task"));
        openTaskButton.getStyleClass().add("btn-primary");
        openTaskButton.setMaxWidth(Double.MAX_VALUE);
        openTaskButton.setStyle("-fx-font-size: 15px; -fx-padding: 14;");
        VBox.setMargin(openTaskButton, new Insets(15, 0, 0, 0));
        openTaskButton.setOnAction(e -> app.openSelectedRepairTask(
                repairsTable.getSelectionModel().getSelectedItem()));
        root.getChildren().add(openTaskButton);

        return root;
    }
}