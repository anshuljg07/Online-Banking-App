import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The AccountHolderSelectionPageController serves as the controller for the AccountHolderSelectionPage.fxml and loads the
 * content for the user and takes in user input
 */
public class AccountHolderSelectionPageController {

    /**
     * The returnHomeBtn is the Button object that will return the user to the HomePage.fxml
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The selectedAccountBtn is the Button object that will take the user to the account home page
     */
    @FXML
    private Button selectedAccountBtn;

    /**
     * The ScrollPane errorScrollPane is the ScrollPane that houses the errors generated while on this page
     */
    @FXML
    private ScrollPane errorScrollPane;

    /**
     * The TextArea errorLog is the TextArea that the error messages that are appended to the TextArea
     */
    @FXML
    private TextArea errorLog;

    /**
     * The Button submitSelectonBtn is the Button that allows to user to select the selectedAccountHolder
     */
    @FXML
    private Button submitSelectonBtn;

    /**
     * The ChoiceBox(AccountHolder) accountHolderChoiceBox is the choicebox that displays all of the account holders that
     * have been created and are a part of the AccountHolderList for the user to choose from
     */
    @FXML
    private ChoiceBox<AccountHolder> accountHolderChoiceBox;

    /**
     * The Label accountSelectedPopUp is the label that shows the selected account from the choice box
     */
    @FXML
    private Label accountSelectedPopUp;

    /**
     * The btnClicked() method deals with MouseEvents and changes the page or loads to selected AccountHolder based on
     * the event generated
     * @param event the MouseEvent
     * @throws IOException if the scene is unable to be changed throw an io exception
     */
    @FXML
    void btnClicked(MouseEvent event) throws IOException {
        if (event.getSource() == returnHomeBtn) {
            Stage thisStage = (Stage) returnHomeBtn.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load(), 600, 350);
            thisStage.setScene(homeScene);
            thisStage.show();
        }
        else if (event.getSource() == submitSelectonBtn) {
            accountSelectedPopUp.setVisible(true);
            selectedAccountBtn.setVisible(true);
            AccountHolder selectedAccount = accountHolderChoiceBox.getValue();
            selectedAccount.setBalance(selectedAccount.getBalance().doubleValue());
            Context.getContext().setSelectedAccount(selectedAccount);
            errorLog.setText(selectedAccount.toString());
            errorScrollPane.setVisible(true);
        }
        else if(event.getSource() == selectedAccountBtn){
            //TODO: LINK TO THE ACCOUNT PAGE, WHERE ONE CAN CHECK BALANCE, DEPOSIT SWD, WITHDRAW SWD, OR CLOSE ACCOUNT.
            Stage thisStage = (Stage) selectedAccountBtn.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountActionsPage.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load(), 600, 400);
            thisStage.setScene(nextScene);
            thisStage.show();


        }
    }

    /**
     * The initialize() method initializes all of the components and then also loads the choicebox with the accountholders
     * from the accounholderlist
     */
    @FXML
    void initialize(){
        selectedAccountBtn.setVisible(false);
        accountSelectedPopUp.setVisible(false);
        errorScrollPane.setVisible(false);
        errorLog.setEditable(false);
        Bank globalBank = Context.getContext().getBank();
        ArrayList<AccountHolder> accounts = globalBank.getAccountHolderList().getAccountHolderList();

        //code from stackoverflow user: jaxcv45
        ObservableList<AccountHolder> accountsList = FXCollections.observableArrayList();
        accountHolderChoiceBox.setItems(accountsList);
        accountsList.addAll(accounts);
        //end code from jaxcv45
    }
}

    