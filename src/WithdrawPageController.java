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
 * The WithdrawPageController is the controller that defines the functionality fro the WithdrawPage.fxml and defines the main
 * function of withdrawing SWD from the account
 */
public class WithdrawPageController {

    /**
     * The TextField withrdawAmounTxtField is where the user inputs the amount to withdraw from the account
     */
    @FXML
    private TextField withdrawAmountTxtField;

    /**
     * The Button withdrawBtn is the button that the user used to finalize the withdrawal
     */
    @FXML
    private Button withdrawBtn;

    /**
     * The Button returnHomeBtn is the that the user uses to return home
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The Button accountHomeBtn is the button that the user uses to return to the account home page
     */
    @FXML
    private Button accountHomeBtn;

    /**
     * The ScrollPane changeOutputScroll is where the withdrawn money is show in SWD currency
     */
    @FXML
    private ScrollPane changeOutputScroll;

    /**
     * The TextArea changeOutputLog is the textArea whre the change generated in SWD will be output
     */
    @FXML
    private TextArea changeOutputLog;

    /**
     * The Label SWDBalance is the account balance in SWD
     */
    @FXML
    private Label SWDBalance;

    /**
     * The Label gowdaIDLabel is the label that will be populated wth the account holder's gowda id
     */
    @FXML
    private Label gowdaIDLabel;

    /**
     * The label accountHolderNameLabel is the label that will hold the account holder's name
     */
    @FXML
    private Label accountHolderNameLabel;

    /**
     * The Label USDBalance is the balance in USD of the account holder
     */
    @FXML
    private Label USDBalance;

    /**
     * The brnClicked() method does functions based upong the button that is clicked.
     * @param event
     */
    @FXML
    void btnClicked(MouseEvent event) {
        if (event.getSource() == returnHomeBtn){
            changeScene(returnHomeBtn, "HomePage.fxml", 600, 350);
        }
        else if(event.getSource() == accountHomeBtn){
            changeScene(accountHomeBtn, "AccountActionsPage.fxml", 600, 400);

        }
        else if(event.getSource() == withdrawBtn){
            System.out.println("CLICKED WITHDRAW");
            AccountHolder ah = Context.getContext().getSelectedAccount();
            Bank b = Context.getContext().getBank();
            double withdrawAmount = 0;

            try{
               withdrawAmount = Double.parseDouble(withdrawAmountTxtField.getText());
            }
            catch (Exception e){
                changeOutputLog.setText("INVALID WITHDRAW AMOUNT ENTERED. Input Rejected");
                changeOutputScroll.setVisible(true);
                return;
            }

            try{
                boolean worked = b.withdraw(ah, withdrawAmount);
                if(worked){
                    changeOutputScroll.setVisible(true);
                    changeOutputLog.setText(b.SWDBillConversion(withdrawAmount));
                    USDBalance.setText(String.valueOf(ah.getBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
                    SWDBalance.setText(String.valueOf(ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP).doubleValue()));
                }
                else{
                    throw new Exception("OVERDRAFT ERROR. INSUFFICIENT BALANCE");
                }
            }
            catch (Exception e){
                changeOutputLog.setText(e.getMessage());
                changeOutputScroll.setVisible(true);
            }
        }
    }

    /**
     * The initialize() method initializes all of the components of the page and also gets the selected account
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

    /**
     * The changeScene method changes the scene to the scene specified by the fxml in the parameters.
     * @param origin the Node object that fires the method call
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
