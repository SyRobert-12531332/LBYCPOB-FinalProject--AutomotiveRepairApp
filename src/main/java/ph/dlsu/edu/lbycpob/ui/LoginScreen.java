package ph.dlsu.edu.lbycpob.ui;

import ph.dlsu.edu.lbycpob.AutomationRepairApp;
import ph.dlsu.edu.lbycpob.util.UIUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Login page. Equivalent of CreateLoginScreen() in the original app.
 */
public class LoginScreen {

    private final AutomationRepairApp app;
    private final StackPane view;
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();

    public LoginScreen(AutomationRepairApp app) {
        this.app = app;
        this.view = build();
    }

    public StackPane getView() {
        return view;
    }

    public void clearFields() {
        usernameField.clear();
        passwordField.clear();
    }

    private StackPane build() {
        StackPane background = new StackPane();
        background.setStyle("-fx-background-color: #f1f5f9;");

        VBox card = new VBox(15);
        card.getStyleClass().add("login-card");
        card.setMaxWidth(420);
        card.setPadding(new Insets(50, 40, 50, 40));
        card.setAlignment(Pos.CENTER);
        card.setEffect(UIUtil.createShadow());

        card.getChildren().add(UIUtil.loadLogo(220, 150));

        Label subtitle = new Label("AutoWorks Mechanics' Automation Documentation");
        subtitle.getStyleClass().add("muted-label");
        subtitle.setFont(Font.font("Segoe UI", 10));

        Label usernameLabel = new Label("Full Name");
        usernameLabel.getStyleClass().add("field-label");
        usernameField.setPromptText("Enter your full name");

        Label passwordLabel = new Label("Password");
        passwordLabel.getStyleClass().add("field-label");
        passwordField.setPromptText("Enter your password");

        Button loginButton = UIUtil.pointer(new Button("Log In"));
        loginButton.getStyleClass().add("btn-primary");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> app.attemptLogin(usernameField.getText().strip(), passwordField.getText().strip()));

        Button signupButton = UIUtil.pointer(new Button("Sign Up"));
        signupButton.getStyleClass().add("btn-link");
        signupButton.setOnAction(e -> app.openSignup());

        VBox spacerAfterSubtitle = new VBox();
        spacerAfterSubtitle.setPrefHeight(20);

        card.getChildren().addAll(
                subtitle,
                spacerAfterSubtitle,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                loginButton, signupButton
        );

        background.getChildren().add(card);
        return background;
    }
}