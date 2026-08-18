package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * Shared base for every screen that shows the standard page header
 * (title + optional "Back to Dashboard" button + logout button + divider).
 * Centralizes the boilerplate that DashboardScreen, LogRepairsScreen,
 * ManageRepairsScreen, and ManageVehiclesScreen previously repeated by hand:
 * creating a padded VBox, styling its background, and adding a new
 * TopNavBar(app, title, showBackButton) as the first child.
 *
 * Subclasses only implement buildContent(root) to add their own controls
 * below the header — they never touch the header or TopNavBar directly.
 */
public abstract class AppScreen {

    protected final AutomationRepairApp app;
    private final VBox root;

    protected AppScreen(AutomationRepairApp app, String title, boolean showBackButton) {
        this.app = app;
        this.root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");
        root.getChildren().add(new TopNavBar(app, title, showBackButton).getRoot());
        // DO NOT call buildContent(root) here
    }

    /**
     * Called once, during construction, after the header has already been
     * added to {@code root}. Implementations add their own screen-specific
     * controls (tables, forms, tiles, etc.) to {@code root}.
     */
    public void init() {
        buildContent(root);
    }

    protected abstract void buildContent(VBox root);

    public VBox getView() {
        return root;
    }
}