package ph.dlsu.edu.lbycpob.util;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 * Equivalent of the original app's ShowPopup() helper: a small wrapper
 * around a dialog so every screen shows errors/success messages the
 * same way.
 */
public final class AlertUtil {

    private AlertUtil() {
    }

    public static void showWarning(String title, String header, String content) {
        show(Alert.AlertType.WARNING, title, header, content);
    }

    public static void showInfo(String title, String header, String content) {
        show(Alert.AlertType.INFORMATION, title, header, content);
    }

    public static void showError(String title, String header, String content) {
        show(Alert.AlertType.ERROR, title, header, content);
    }

    private static void show(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        DialogPane pane = alert.getDialogPane();
        pane.setStyle("-fx-background-color: #393e3f;");
        pane.lookupAll(".content.label").forEach(node -> node.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"));
        pane.lookupAll(".header-panel").forEach(node -> node.setStyle("-fx-background-color: #393e3f;"));

        alert.showAndWait();
    }
}
