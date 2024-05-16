import Presentation.View;
import Presentation.Controller;
import javax.swing.*;

/**
 * The Main class is the entry point of the application.
 * It creates an instance of the View and Controller classes and sets the view visible.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It uses the SwingUtilities.invokeLater method to ensure that the GUI creation is done on the Event-Dispatching thread.
     * This is a good practice because it prevents potential concurrency issues in Swing applications.
     * @param args Command-line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            View view = new View();
            new Controller(view);
            view.setVisible(true);
        });
    }
}