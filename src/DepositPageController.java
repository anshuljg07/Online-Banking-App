import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.RoundingMode;

/**
 * The DepositPageController is the controller for the DepositPage.fxml and defines the functionality of the page associated wiht
 * deposits.
 */
public class DepositPageController {

    /**
     * The TextField depositAmountTxtField is the TextField that takes the amount the user wishes to deposit into their bank account
     */
    @FXML
    private TextField depositAmountTxtField;

    /**
     * The Button depositBtn is the button the user clicks to finalize the deposit transaction
     */
    @FXML
    private Button depositBtn;

    /**
     * The returnHomeBtn is the Button that the user uses to return to the Home Page
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The Button accountHomebtn is the button the user uses to return to the account editing home page
     */
    @FXML
    private Button accountHomeBtn;

    /**
     * The ScrollPane changeOutputScroll is the scroll pane that houses the textarea where the amount in SWD is shown
     */
    @FXML
    private ScrollPane changeOutputScroll;

    /**
     * The TextArea changeOutputLog has the string output of the amount in SWD that was deposited
     */
    @FXML
    private TextArea changeOutputLog;

    /**
     * The Label SWDBalance is the balance in SWD
     */
    @FXML
    private Label SWDBalance;

    /**
     * The Label gowdaIDLabel shows the gowda id of the selected account
     */
    @FXML
    private Label gowdaIDLabel;

    /**
     * The Label accountHolderNameLabel shows the account holder's name from the selected account
     */
    @FXML
    private Label accountHolderNameLabel;

    /**
     * The Label USDBalance is the balance in USD for the user to see
     */
    @FXML
    private Label USDBalance;

    /**
     * The btnclicked() method defines the functionality when dealing with button presses. The main functionality is that it submits
     * the deposit transaction and prints if succesfull
     * @param event MouseEvent
     */
    @FXML
    void btnClicked(MouseEvent event) {
        if (event.getSource() == accountHomeBtn) {
            changeScene(accountHomeBtn, "AccountActionsPage.fxml", 600, 400);
        } else if (event.getSource() == returnHomeBtn) {
            changeScene(returnHomeBtn, "HomePage.fxml", 600, 350);

        } else if (event.getSource() == depositBtn) {
            AccountHolder ah = Context.getContext().getSelectedAccount();
            Bank b = Context.getContext().getBank();
            try{
                double depositAmount = Float.parseFloat(depositAmountTxtField.getText());
                b.deposit(ah, depositAmount);
                USDBalance.setText(String.valueOf(ah.getBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
                SWDBalance.setText(String.valueOf(ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
                changeOutputScroll.setVisible(false);
                changeOutputLog.clear();
            }
            catch (Exception e){
                changeOutputLog.setText("INVALID DEPOSIT AMOUNT ENTERED. Input Rejected");
                changeOutputScroll.setVisible(true);
            }
        }
    }

    /**
     * The changeScene() method changes the Scene to a new scene defined by an fxml file given as an arguement
     * @param origin the Node object that fired the method call
     * @param fxmlFileName the fxml file associated with the new scene
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
     * The initialize method initializes the components of the page and fetches the selected account
     */
    @FXML
    void initialize(){
        changeOutputScroll.setVisible(false);
        changeOutputLog.clear();

        AccountHolder ah = Context.getContext().getSelectedAccount();
        USDBalance.setText(String.valueOf(ah.getBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        SWDBalance.setText(String.valueOf(ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));

        gowdaIDLabel.setText("GOWDA ID: " + ah.getBankAccountHolderID());
        accountHolderNameLabel.setText(ah.getAccountHolderName());
    }
}