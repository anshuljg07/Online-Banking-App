import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The AccountActionsPageController serves as the Controller class for the AccountActionsPage.fxml and implements the necessary
 * functionality
 */
public class AccountActionsPageController {

    /**
     * The TexArea accountTitle contains the account information such as the Gowda ID and the account holder's name, which
     * are defined in the initialize function
     */
    @FXML
    private TextArea accountTitle;
    /**
     * The Button returnHomeBtn is the button that returns back to the HomePage.fxml
     */
    @FXML
    private Button returnHomeBtn;
    /**
     * The Label SWDBalance serves as the textual representation of the account holder's balance in SWD currency
     */
    @FXML
    private Label SWDBalance;

    /**
     * The Label USDBalance serves as the textual representation of the account holder's balance in USD currency
     */
    @FXML
    private Label USDBalance;

    /**
     * The Button closeAccountBtn is the button through which the user can navigate to the AccountClosedPopUpPage.fxml
     */
    @FXML
    private Button closeAccountBtn;

    /**
     * The withdrawSWDBtn is the button through which the user can naviaate to the WithdrawPage.fxml
     */
    @FXML
    private Button withdrawSWDBtn;

    /**
     * The depositSWDBtn is the button through which the user can navigate to the DepositPage.fxml
     */
    @FXML
    private Button depositSWDBtn;


    /**
     * The btnClicked() method takes a parameter event and decides which fxml to change to base don user input
     * @param event a MouseEvent that is generated
     */
    @FXML
    void btnClicked(MouseEvent event) {
        if(event.getSource() == depositSWDBtn){
            changeScene(depositSWDBtn, "DepositPage.fxml", 600, 400);
        }
        else if(event.getSource() == withdrawSWDBtn){
            changeScene(withdrawSWDBtn, "WithdrawPage.fxml", 600, 400);
            
        }
        else if (event.getSource() == closeAccountBtn) {
            changeScene(closeAccountBtn, "AccountClosedPopUpPage.fxml", 600, 420);
        }
        else if (event.getSource() == returnHomeBtn){
            changeScene(returnHomeBtn, "HomePage.fxml", 600, 350);
        }
    }

    /**
     * The changeScene method gets the Stage from the origin of the method call and then loads the new scene to be shown.
     * Then changes the stage's scene to the new scene and displays it.
     * @param origin the Node object that results in this call
     * @param fxmlFileName the new fxml to be loaded as a scene
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

    /**
     * The initialize() initiliazes all of the comonenet fields with the attributes of the AccountHolder selectedAccount
     */
    @FXML
    void initialize(){
        accountTitle.setEditable(false);
        AccountHolder selectedAccount = Context.getContext().getSelectedAccount();
        accountTitle.setText("GOWDA ID: " + selectedAccount.getBankAccountHolderID() + " | " + selectedAccount.getAccountHolderName());
        SWDBalance.setText(selectedAccount.getSWDBalance().toString());
        USDBalance.setText(selectedAccount.getBalance().toString());
    }
}