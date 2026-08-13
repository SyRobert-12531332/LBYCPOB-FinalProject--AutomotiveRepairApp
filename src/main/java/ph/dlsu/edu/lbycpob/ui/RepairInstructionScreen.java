package com.autoworks.repair.ui;

import com.autoworks.repair.AutomationRepairApp;
import com.autoworks.repair.model.RepairJob;
import com.autoworks.repair.util.UIUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static com.autoworks.repair.util.UIUtil.pointer;

/**
 * Repair instruction / progress page: shows (and allows editing of) the
 * standard operating procedure for a part, plus a slider to record how
 * far along the repair is. Equivalent of CreateRepairInstructionScreen()
 * in the original app.
 */
public class RepairInstructionScreen {

    private final AutomationRepairApp app;
    private final VBox view;

    private final Label titleLabel = new Label("Repairing: Unknown Part");
    private final Label subtitleLabel = new Label("Vehicle: -- | Urgency: --");
    private final TextArea instructionBox = new TextArea();
    private final Label progressLabel = new Label("Current Progress: 0%");
    private final Slider progressSlider = new Slider(0, 100, 0);

    public RepairInstructionScreen(AutomationRepairApp app) {
        this.app = app;
        this.view = build();
    }

    public VBox getView() {
        return view;
    }

    /** Populates the screen for a newly-opened repair task. */
    public void open(RepairJob job, String instructions) {
        String partName = job.getPart() != null ? job.getPart() : "Unknown Part";
        titleLabel.setText("Repairing: " + partName);
        subtitleLabel.setText("Vehicle: " + nullSafe(job.getPlate()) + " | Urgency: " + nullSafe(job.getSeverity()));
        instructionBox.setText(instructions);
        progressSlider.setValue(job.getProgress());
        progressLabel.setText("Current Progress: " + job.getProgress() + "%");
    }

    private String nullSafe(String value) {
        return value != null ? value : "N/A";
    }

    private VBox build() {
        VBox root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: #0f172a;");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        javafx.scene.Node logo = UIUtil.loadLogo(150, 150);
        header.getChildren().addAll(titleLabel, spacer, logo);
        root.getChildren().add(header);

        subtitleLabel.getStyleClass().add("muted-label");
        VBox.setMargin(subtitleLabel, new Insets(4, 0, 15, 0));
        root.getChildren().add(subtitleLabel);

