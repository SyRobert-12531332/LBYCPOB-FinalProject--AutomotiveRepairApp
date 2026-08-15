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
    private StackPane build() {
        StackPane root = new StackPane();
        root.setPadding(new Insets(20));

        VBox card = new VBox(10);
        card.setMaxWidth(350);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));

        Label titleLabel = new Label("Create Account");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

        emailField.setPromptText("Email");
        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        passwordField.setPromptText("Password");

        Button signupButton = new Button("Sign Up");
        signupButton.setDefaultButton(true);
        signupButton.setOnAction(e -> handleSignup());

        Button loginButton = new Button("Back to Login");
        loginButton.setOnAction(e -> app.showLoginScreen());

        HBox buttonBox = new HBox(10, signupButton, loginButton);
        buttonBox.setAlignment(Pos.CENTER);

        card.getChildren().addAll(
                titleLabel,
                emailField,
                firstNameField,
                lastNameField,
                passwordField,
                buttonBox
        );

        root.getChildren().add(card);
        return root;
    }

    private void handleSignup() {
        String email = emailField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            UIUtil.showAlert("Error", "Please fill in all fields.");
            return;
        }

        // Add your registration logic/database call here via app instance
        clearFields();
        app.showLoginScreen();
    }
}