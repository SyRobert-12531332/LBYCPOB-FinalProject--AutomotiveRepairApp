package ph.dlsu.edu.lbycpob.util;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.InputStream;

/**
 * Small collection of shared UI helpers, equivalent to the
 * CreateShadow() helper and repeated logo-loading code in the
 * original Python app.
 */
public final class UIUtil {

    private UIUtil() {
    }

    /** Recreates the soft drop shadow (blur 25, black @ ~6% opacity, offset y=4) used everywhere in the original app. */
    public static DropShadow createShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(25);
        shadow.setColor(Color.rgb(0, 0, 0, 0.06));
        shadow.setOffsetX(0);
        shadow.setOffsetY(4);
        return shadow;
    }

    /** Gives a node a pointing-hand cursor, matching QCursor(Qt.PointingHandCursor) usage on buttons. */
    public static <T extends Node> T pointer(T node) {
        node.setCursor(Cursor.HAND);
        return node;
    }

    // Add this method to resolve UIUtil.showAlert(...)
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Loads the AutoWorks logo from the classpath (src/main/resources/images/autoworkslogo.png).
     * If the asset is missing, returns an empty label instead of crashing, since these image
     * files were not part of the source code that was converted.
     */
    public static Node loadLogo(double maxWidth, double maxHeight) {
        try (InputStream stream = UIUtil.class.getResourceAsStream("/images/autoworkslogo.png")) {
            if (stream == null) {
                return new Label();
            }
            Image image = new Image(stream);
            ImageView view = new ImageView(image);
            view.setPreserveRatio(true);
            view.setFitWidth(maxWidth);
            view.setFitHeight(maxHeight);
            return view;
        } catch (Exception e) {
            return new Label();
        }
    }
}
