import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The BankApp Class is the driver code for ExchangeComputationGUI and it creates the Stage and sets the primary scene
 * before it being sent of to the controllers.
 */
public class BankApp extends Application {

    /**
     * The main method starts the application
     * @param args arguements provided to the main method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start() method instantiates and initializes the main stage and sets the first scene to the HomePage.fxml.
     * @param primaryStage the main stage that holds the scences
     * @throws IOException if the scene is not able to be changed
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
        primaryStage.setTitle("Bank of Gowda GUI");

        Bank bank = new Bank();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}