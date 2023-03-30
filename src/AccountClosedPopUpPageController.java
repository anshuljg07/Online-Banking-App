import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.RoundingMode;

/**
 * The AccountClosedPopUpPageController controls the responses and loading of the AccountClosedPopUpPageController.fxml.
 * Which is loaded when the account is wished to be closed by the user.
 */
public class AccountClosedPopUpPageController {

    /**
     * The Label accountDeletedTitle is the title for the acccountDelteTitle and contains the gowda id and the account holder
     * name
     */
    @FXML
    private Label accountDeletedTitle;

    /**
     * The Label SWDBalance is the selected AccountHolder's SWD balance
     */
    @FXML
    private Label SWDBalance;
    /**
     * The Label USDBalnce is the selected AccountHolder's SWD balance
     */
    @FXML
    private Label USDBalance;

    /**
     * The Label accountNameLabel is the selected AccountHolder's account name
     */
    @FXML
    private Label accountNameLabel;

    /**
     * The Button returnHomeBtn is the button that returns the user back to the HomePage.fxml
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The ScrollPane changeOutputScroll contains the money in USD leftover in the users account after their account is clsoed
     */
    @FXML
    private ScrollPane changeOutputScroll;

    /**
     * The TextArea changeOutputLog contains the change leftover in USD from the account
     */
    @FXML
    private TextArea changeOutputLog;

    /**
     * The btnClicked() method returns the user back to the hompage
     * @param event
     */
    @FXML
    void btnClicked(MouseEvent event) {
        if (event.getSource() == returnHomeBtn) {
            changeScene(returnHomeBtn, "HomePage.fxml", 600, 350);
        }
    }

    @FXML
    void txtFieldEntered(KeyEvent event) {

    }

    /**
     * The initialize function intializes all of the component instance variables and closes the selected Account Holder
     */
    @FXML
    void initialize(){
        AccountHolder ah = Context.getContext().getSelectedAccount();
        Bank b = Context.getContext().getBank();

        USDBalance.setText(String.valueOf(ah.getBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        SWDBalance.setText(String.valueOf(ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
        changeOutputLog.setText(b.printUSDBills(ah));
        changeOutputScroll.setVisible(true);

        accountDeletedTitle.setText("GOWDA ID: " + ah.getBankAccountHolderID() + " Account Deleted");
        accountNameLabel.setText(ah.getAccountHolderName());

        USDBalance.setDisable(true);
        SWDBalance.setDisable(true);
        accountNameLabel.setDisable(true);

        b.closeAccount(ah);
    }

    /**
     * The changeScene method changes the scene of the stage to the fxml file specified in the method parameter
     * @param origin the component that fired this method call
     * @param fxmlFileName the fxml file corresponding to the new scene
     * @param newWidth the new with of the new scene
     * @param newHeight the new height of the new scene
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
