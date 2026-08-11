package ph.dlsu.edu.lbycpob.ui;

import com.autoworks.repair.AutomationRepairApp;
import com.autoworks.repair.util.UIUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Reusable page header: title on the left, logo + navigation buttons on the
 * right, and a divider line underneath. Every non-login screen in the
 * original app rebuilt this same layout by hand (title label, spacer,
 * logo, back button, logout button, divider); this component centralizes it.
 */
public final class TopNavBar {

    private final VBox root = new VBox();

    public TopNavBar(AutomationRepairApp app, String title, boolean showBackButton) {
        HBox nav = new HBox(12);
        nav.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titleLabel.setStyle("-fx-text-fill: #0f172a;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        nav.getChildren().addAll(titleLabel, spacer, UIUtil.loadLogo(150, 150));

        if (showBackButton) {
            Button backButton = UIUtil.pointer(new Button("Back to Dashboard"));
            backButton.getStyleClass().add("btn-ghost");
            backButton.setOnAction(e -> app.openDashboard());
            nav.getChildren().add(backButton);
        }

        Button logoutButton = UIUtil.pointer(new Button("Log Out"));
        logoutButton.getStyleClass().add("btn-primary");
        logoutButton.setStyle("-fx-padding: 8 16 8 16; -fx-font-size: 13px;");
        logoutButton.setOnAction(e -> app.logout());
        nav.getChildren().add(logoutButton);

        Region divider = new Region();
        divider.getStyleClass().add("divider");
        divider.setPrefHeight(2);
        divider.setMaxHeight(2);
        divider.setStyle("-fx-background-color: #e2e8f0;");
        VBox.setMargin(divider, new javafx.geometry.Insets(10, 0, 25, 0));

        root.getChildren().addAll(nav, divider);
    }

    public VBox getRoot() {
        return root;
    }
}