package ph.dlsu.edu.lbycpob;

import ph.dlsu.edu.lbycpob.model.RepairJob;
import ph.dlsu.edu.lbycpob.model.User;
import ph.dlsu.edu.lbycpob.model.Vehicle;
import ph.dlsu.edu.lbycpob.service.InstructionsService;
import ph.dlsu.edu.lbycpob.service.PartsService;
import ph.dlsu.edu.lbycpob.service.RepairService;
import ph.dlsu.edu.lbycpob.service.UserService;
import ph.dlsu.edu.lbycpob.service.VehicleService;
import ph.dlsu.edu.lbycpob.ui.DashboardScreen;
import ph.dlsu.edu.lbycpob.ui.LogRepairsScreen;
import ph.dlsu.edu.lbycpob.ui.LoginScreen;
import ph.dlsu.edu.lbycpob.ui.ManageRepairsScreen;
import ph.dlsu.edu.lbycpob.ui.ManageVehiclesScreen;
import ph.dlsu.edu.lbycpob.ui.RepairInstructionScreen;
import ph.dlsu.edu.lbycpob.ui.SignupScreen;
import ph.dlsu.edu.lbycpob.util.AlertUtil;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AutomationRepairApp extends Application {

    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // --- Services (equivalent to the file read/write helper methods) ---
    private final UserService userService = new UserService();
    private final VehicleService vehicleService = new VehicleService();
    private final RepairService repairService = new RepairService();
    private final PartsService partsService = new PartsService();
    private final InstructionsService instructionsService = new InstructionsService();

    // --- Shared mutable state ---
    private String currentMechanicName = "Admin";
    private Map<String, String> partInstructions;
    private Map<String, List<String>> partsDictionary;
    private List<RepairJob> allRepairsData;
    private RepairJob currentActiveRepair;
    private String currentSelectedVehicleType;

    // --- Screens ---
    private LoginScreen loginScreen;
    private SignupScreen signupScreen;
    private DashboardScreen dashboardScreen;
    private ManageVehiclesScreen manageVehiclesScreen;
    private LogRepairsScreen logRepairsScreen;
    private ManageRepairsScreen manageRepairsScreen;
    private RepairInstructionScreen instructionScreen;

    private StackPane screenStack;

    @Override
    public void start(Stage primaryStage) {
        partInstructions = instructionsService.loadInstructions();
        partsDictionary = partsService.loadParts();

        loginScreen = new LoginScreen(this);
        signupScreen = new SignupScreen(this);
        dashboardScreen = new DashboardScreen(this);
        manageVehiclesScreen = new ManageVehiclesScreen(this);
        logRepairsScreen = new LogRepairsScreen(this);
        manageRepairsScreen = new ManageRepairsScreen(this);
        instructionScreen = new RepairInstructionScreen(this);

        screenStack = new StackPane(
                loginScreen.getView(),
                signupScreen.getView(),
                dashboardScreen.getView(),
                manageVehiclesScreen.getView(),
                logRepairsScreen.getView(),
                manageRepairsScreen.getView(),
                instructionScreen.getView()
        );

        showOnly(loginScreen.getView());

        Scene scene = new Scene(screenStack, 1200, 750);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("Automation Repair App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Live clock, updated every second - mirrors the QTimer(1000ms) in the original app.
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    // ------------------------------------------------------------------
    // Navigation
    // ------------------------------------------------------------------

    private void showOnly(javafx.scene.Node node) {
        for (javafx.scene.Node child : screenStack.getChildren()) {
            child.setVisible(child == node);
            child.setManaged(child == node);
        }
    }

    public void openLogin() {
        loginScreen.clearFields();
        showOnly(loginScreen.getView());
    }

    public void openSignup() {
        showOnly(signupScreen.getView());
    }

    public void logout() {
        loginScreen.clearFields();
        showOnly(loginScreen.getView());
    }

    // ------------------------------------------------------------------
    // Login / Signup
    // ------------------------------------------------------------------

    public void attemptLogin(String fullName, String password) {
        Optional<User> match = userService.authenticate(fullName, password);
        if (match.isPresent()) {
            currentMechanicName = match.get().getFullName();
            openDashboard();
        } else {
            AlertUtil.showWarning("Log in Failed", "Log in Failed", "Invalid username or password.");
        }
    }

    public void registerUser(String firstName, String lastName, String email, String password) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            AlertUtil.showWarning("Error", "Error", "Please fill out all fields.");
            return;
        }
        userService.registerUser(new User(firstName, lastName, email, password));
        AlertUtil.showInfo("Success", "Success", "Account created successfully! You can now log in.");
        signupScreen.clearFields();
        openLogin();
    }

    public void openDashboard() {
        dashboardScreen.refresh(repairService.loadRepairsSortedByUrgency());
        showOnly(dashboardScreen.getView());
    }

    public void openManageVehicles() {
        manageVehiclesScreen.refresh(vehicleService.loadVehicles());
        showOnly(manageVehiclesScreen.getView());
    }

    public static void main(String[] args) {
        launch(args);
    }
}