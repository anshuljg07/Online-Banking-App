import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HomePageController is the controller for the HomePage.fxml and defines the functionality assocaited with the home
 * page. It allows for creation of accounts, selecting accounts, and changing the exchange rate
 */
public class HomePageController {

    /**
     * The Button exRateBtn is the button that is used to navigate to the change exchange rate page
     */
    @FXML
    private Button exRateBtn;

    /**
     * The Button newAccountBtn navigates the user to the page where they can make a new account
     */
    @FXML
    private Button newAccountBtn;

    /**
     * The Button selectAccountBtn navigates the user to the page where they can select an existing account
     */
    @FXML
    private Button selectAccountBtn;

    /**
     * The changePage() method changes the scene based on the button that is pressed
     * @param event MouseEvent
     */
    @FXML
    void changePage(MouseEvent event) {
        System.out.println(event.getEventType());
        if(event.getSource() == exRateBtn){
            System.out.println("Change exchange rate");
            changeScene(exRateBtn, "ExchangeRatePage.fxml", 605, 425);
        }
        else if(event.getSource() == selectAccountBtn){
            System.out.println("Select Accounts Page");
            changeScene(selectAccountBtn, "AccountHolderSelectionPage.fxml", 600, 400);
        }
        else if(event.getSource() == newAccountBtn){
            System.out.println("New Accounts Page");
            changeScene(newAccountBtn, "CreateAccountPage.fxml", 605, 425);
        }
    }

    /**
     * The changeScene method changes the scene of the Stage to specified fxml in the parameters
     * @param origin the Node object that fired the method call
     * @param fxmlFileName the fxml for the new scene
     * @param newWidth the width of the new scene
     * @param newHeight the height of the new scene
     */
    public void changeScene(Node origin, String fxmlFileName, int newWidth, int newHeight){
        try{
            Stage thisStage = (Stage) origin.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Scene nextScene = new Scene(fxmlLoader.load(), newWidth, newHeight);
            thisStage.setScene(nextScene);
            thisStage.show();
        }
        catch (IOException e){
            System.out.println("Error Changing Scene from Main Page");
            System.exit(0);
        }
    }
}
