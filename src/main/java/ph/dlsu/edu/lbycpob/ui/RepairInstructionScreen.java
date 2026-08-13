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

