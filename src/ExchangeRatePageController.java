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
import java.math.RoundingMode;

/**
 * The ExchangeRatePageController is the controller for the ExchangeRatePage.fxml and defines the functionality associated wiht
 * the page and defines the functionality to change the exchange rate
 */
public class ExchangeRatePageController{

    /**
     * The TextField currentExRateTextField is the text field that shows the current exchange rate of the bank
     */
    @FXML
    private TextField currentExRateTxtField;

    /**
     * The TextField newExRateTxtField is the field where the user can input the new exchange rate of the bank
     */
    @FXML
    private TextField newExRateTxtField;

    /**
     * The Button submitExcRateBtn is the button the user can press to submit their request to change the exchange rate
     */
    @FXML
    private Button submitExcRateBtn;

    /**
     * The returnHomebtn is the button that allows the user to return to the HomePage
     */
    @FXML
    private Button returnHomeBtn;

    /**
     * The TextArea errorLog houses any errors that are created when the user wishes to change the exchange rate
     */
    @FXML
    private TextArea errorLog;

    /**
     * The ScrollPane errorScrollPane houses the errorLog textarea for the user to see
     */
    @FXML
    private ScrollPane errorScrollPane;

    /**
     * The inputFieldEntered() method makes the submit button visible if an exchange rate is entered
     * @param event a KeyEvent
     */
    @FXML
    public void inputFieldEntered(KeyEvent event) {
        if (event.getSource() == newExRateTxtField){
            submitExcRateBtn.setVisible(true);
        }
    }

    /**
     * The btnClicked() method defines the functionality of button presses of the various button componenets. The main functionnality
     * is that if a new exchange rate is submitted it changes the exchange rate and creates an error to add to the errorlog if
     * improper exvhange rate is given
     * @param mouseEvent MouseEvent
     * @throws IOException if the scene change is not successfull
     */
    @FXML
    public void btnClicked(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == submitExcRateBtn){
            try {
                String userExRate = newExRateTxtField.getText();
                Float userExRatefloat = Float.parseFloat(userExRate);
                Bank.setConversionRate(userExRatefloat);
//                Context.getContext().getBank().setConversionRate(userExRatefloat);
                currentExRateTxtField.setText(String.valueOf(userExRatefloat));
                errorScrollPane.setVisible(false);
                errorLog.clear();
            }
            catch (Exception e){
                    errorLog.setText("INVALID EXCHANGE RATE ENTERED. Input Rejected");
                    errorScrollPane.setVisible(true);
            }
        }
        else if(mouseEvent.getSource() == returnHomeBtn){
            Stage thisStage = (Stage) returnHomeBtn.getParent().getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Scene homeScene = new Scene(fxmlLoader.load(), 600, 350);
            thisStage.setScene(homeScene);
            thisStage.show();
            //change the current scene to the new one
        }
    }

    /**
     * The initialize() method initializes the components and fetches the curent exchange rate
     */
    @FXML
    public void initialize(){
        System.out.println("ENTERED INITiALIZE");
        newExRateTxtField.requestFocus();
        currentExRateTxtField.setEditable(false);
        submitExcRateBtn.setVisible(false);
        currentExRateTxtField.setText(String.valueOf(Bank.getConversionRate().setScale(4, RoundingMode.HALF_UP)));
        errorLog.setEditable(false);
        errorScrollPane.setVisible(false);
    }
}
//    @FXML
//    void btnSubmitHandler(MouseEvent event) {
//        if(event.getSource() == submitNewExRateBtn){
//
//        }
//
//    }
//
//    @FXML
//    void inputFieldEntered(KeyEvent event) {
//        submitNewExRateBtn.setVisible(true);
//    }
//
//    @FXML
//    public void initialize(){
//        submitNewExRateBtn.setVisible(false);
//    }

