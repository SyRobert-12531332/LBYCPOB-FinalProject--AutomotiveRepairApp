package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public abstract class AppScreen {

    protected final AutomationRepairApp app;
    private final VBox root;

    protected AppScreen(AutomationRepairApp app, String title, boolean showBackButton) {
        this.app = app;
        this.root = new VBox();
        root.setPadding(new Insets(40, 50, 50, 50));
        root.setStyle("-fx-background-color: #f1f5f9;");
        root.getChildren().add(new TopNavBar(app, title, showBackButton).getRoot());
        // Removed buildContent(root) from superclass constructor
    }

    /**
     * Call this at the end of every subclass constructor.
     */
    protected void init() {
        buildContent(root);
    }

    protected abstract void buildContent(VBox root);

    public VBox getView() {
        return root;
    }
}