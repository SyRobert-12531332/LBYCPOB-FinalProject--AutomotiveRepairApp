package ph.dlsu.edu.lbycpob;

import javafx.application.Application;

/**
 * Plain launcher class. Kept separate from AutomationRepairApp so the
 * project also runs cleanly from a fat/shaded jar (some JavaFX runtime
 * setups are picky about the Application subclass being the jar's
 * declared Main-Class).
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(AutomationRepairApp.class, args);
    }
}
