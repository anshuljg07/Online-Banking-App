import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The CreateAccountPageController is the controller for the CreateAccountPage.fxml and allows us to create AccountHolders
 */
public class CreateAccountPageController {

    /**
     * The TextField nameAccountTxtField is where the user inputs the account name for the account they wish to create
     */
    @FXML
    private TextField nameAccountTxtField;

    /**
     * The initialBalanceTxtField contains the initial balance that the new account should
     */
    @FXML
    private TextField initialBalanceTxtField;

    /**
     * The Button createAccountBtn is the button that will be used to create the new account
     */
    @FXML
    private Button createAccountBtn;

    /**
     * The Button returnHomeBtn is the button that will be used to return to the homepage
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The TextArea errorLog is the TextARea that will house any errors that arise while the account is being made
     */
    @FXML
    private TextArea errorLog;

    /**
     * The ScrollPane erroScrollPane holds the TextArea that has all of the errors generated
     */
    @FXML
    private ScrollPane errorScrollPane;

    /**
     * The Button accountEditBtn is the button that takes the user to account editing page
     */
    @FXML
    private Button accountEditBtn;

    /**
     * The btnClicked() method deals with button presses by the client and creates a new account page if the routing is correct
     * and proper parameters are passed
     * @param event the MouseEvent
     * @throws IOException while trying to change scenes
     */
    @FXML
    void btnClicked(MouseEvent event) throws IOException {
        if(event.getSource() == returnHomeBtn){
            Stage thisStage = (Stage) returnHomeBtn.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load(), 600, 350);
            thisStage.setScene(homeScene);
            thisStage.show();
        }
        else if(event.getSource() == createAccountBtn){
            Bank tmpBank = Context.getContext().getBank();
            try {
                if (nameAccountTxtField.getText().length() == 0) {
                    System.out.println("Name: " + nameAccountTxtField);
                    throw new Exception("ACCOUNT NAME FIELD EMPTY. Input Rejected");
                }
                errorScrollPane.setVisible(false);
                errorLog.clear();
            }
            catch (Exception e){
                errorLog.setText(errorLog.getText() + e.getMessage() + "\n");
                errorScrollPane.setVisible(true);
            }
            try {
                BigDecimal initialBalance = new BigDecimal(Float.parseFloat(initialBalanceTxtField.getText()));
                int newAccountID = tmpBank.createNewAccount(nameAccountTxtField.getText(), initialBalance.setScale(2, RoundingMode.HALF_UP).doubleValue());
                Bank globalBank = Context.getContext().getBank();

                try{
                    AccountHolder newAccount = globalBank.getAccountHolderList().getAccountHolder(newAccountID);
                    Context.getContext().setSelectedAccount(newAccount);
                    errorLog.setText("Account Created!\nGOWDA ID (PLEASE WRITE THIS DOWN) : "  + newAccount.getBankAccountHolderID() + "\nAccount Holder's Name: " + newAccount.getAccountHolderName() + "\nBalance: " + newAccount.getBalance());
                    errorScrollPane.setVisible(true);
                    nameAccountTxtField.setEditable(false);
                    initialBalanceTxtField.setEditable(false);
                    accountEditBtn.setVisible(true);
                }
                catch (Exception e){
                    errorLog.setText(e + "\n");
                    errorScrollPane.setVisible(true);
                }
            }
            catch (Exception e){
                errorLog.setText(errorLog.getText() + "INVALID INITIAL BALANCE ENTERED. Input Rejected\n");
                errorScrollPane.setVisible(true);
            }
        }
        else if(event.getSource() == accountEditBtn){
            Stage thisStage = (Stage) accountEditBtn.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountActionsPage.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load(), 600, 400);
            thisStage.setScene(nextScene);
            thisStage.show();
        }
    }

    /**
     * The initialize() method initializes the components on the page
     */
    @FXML
    public void initialize(){
        errorLog.setEditable(false);
        errorScrollPane.setVisible(false);
        accountEditBtn.setVisible(false);
    }
    @FXML
    public void txtFieldEntered(KeyEvent keyEvent) {
    }
}
