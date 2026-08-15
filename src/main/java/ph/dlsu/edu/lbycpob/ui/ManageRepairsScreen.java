package ph.dlsu.edu.lbycpob.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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

    private void setupTable() {
        repairsTable.getStyleClass().add("data-table");
        repairsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<RepairJob, String> urgency = new TableColumn<>("Urgency");
        urgency.setCellValueFactory(d -> new ReadOnlyObjectWrapper<>(d.getValue().getSeverity()));
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
        plate.setCellValueFactory(d -> new ReadOnlyObjectWrapper<>(d.getValue().getPlate()));

        TableColumn<RepairJob, String> part = new TableColumn<>("Part");
        part.setCellValueFactory(d -> new ReadOnlyObjectWrapper<>(d.getValue().getPart()));

        TableColumn<RepairJob, String> mechanic = new TableColumn<>("Mechanic");
        mechanic.setCellValueFactory(d -> new ReadOnlyObjectWrapper<>(d.getValue().getMechanic()));

        TableColumn<RepairJob, String> progress = new TableColumn<>("Progress");
        progress.setCellValueFactory(d -> new ReadOnlyObjectWrapper<>(d.getValue().getProgress() + "%"));

        repairsTable.getColumns().addAll(List.of(urgency, plate, part, mechanic, progress));
    }
}
}