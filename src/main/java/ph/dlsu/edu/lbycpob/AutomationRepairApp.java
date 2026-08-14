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

    public static void main(String[] args) {
        launch(args);
    }
}