package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.model.RepairJob;
import ph.dlsu.edu.lbycpob.util.UIUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Registration page. Equivalent of CreateSignup() in the original app.
 */
public class SignupScreen {

    private final AutomationRepairApp app;
    private final StackPane view;

    private final TextField emailField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final PasswordField passwordField = new PasswordField();

    public SignupScreen(AutomationRepairApp app) {
        this.app = app;
        this.view = build();
    }

    public StackPane getView() {
        return view;
    }

    public void clearFields() {
        emailField.clear();
        firstNameField.clear();
        lastNameField.clear();
        passwordField.clear();
    }
