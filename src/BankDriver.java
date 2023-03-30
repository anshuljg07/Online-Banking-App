import java.math.RoundingMode;
import java.util.Scanner;

/**
 * The BankDriver class is the console based version of the ExchangeComputationGUI, used when developing the base exchange computation
 * code
 */
public class BankDriver {

    /**
     * The main method creates the bank and takes user input for further action in the bank functionality
     * @param args the arguments provided to the main method
     */
    public static void main(String[] args){
        Bank bank1 = new Bank();
        AccountHolder acount1 = new AccountHolder("Anshul",0);

        while(true){
            System.out.println("\n1. Set exchange rate \n2. Check Balance in USD (and converted SWD) \n3. Deposit SWD \n4. Withdraw SWD \n5. Close the Account \n6. Exit");
            double input;
            Scanner userInput = new Scanner(System.in);
            input = validateBankInput(userInput);
            if(input != -1){
                if(input == 1){
                    System.out.println("Input the Exchange Rate:");
                    userInput = new Scanner(System.in);
                    input = validateBankInput(userInput);
                    if(input != -1){
                        bank1.setConversionRate(input);
                        input = 0;
                        System.out.println("Conversion Rate is set to " + Bank.getConversionRate().setScale(4, RoundingMode.HALF_UP));
                    }
                    else{
                        return;
                    }
                }
                else if(input == 2){
                    System.out.println("Balance in USD: " + acount1.getBalance().setScale(2, RoundingMode.HALF_UP) + " Balance in SWD " + acount1.getSWDBalance().setScale(2, RoundingMode.HALF_UP));
                }
                else if(input == 3){
                    System.out.println("Enter Deposit amount in SWD");
                    input = validateBankInput(userInput);
                    if(input != -1){
                        bank1.deposit(acount1, input);
//                        System.out.println("Deposit of " + input + " was successfull. New balance is " + acount1.getBalance().setScale(2, RoundingMode.HALF_UP) + " USD (" + acount1.getSWDBalance().setScale(2, RoundingMode.HALF_UP) + " SWD)");
                    }
                    else{
                        return;
                    }
                }
                else if(input == 4){
                    System.out.println("Enter Withdraw amount in SWD");
                    input = validateBankInput(userInput);
                    if (input != -1){
                        bank1.withdraw(acount1, input);
                    }
                    else {
                        return;
                    }
                }
                else if(input == 5){
                    bank1.closeAccount(acount1);
                }
                else if(input == 6){
                    return;
                }
                else{
                    System.out.println("Enter a valid input");
                }
            }
        }
    }

    /**
     * The validateBankInput method checks to make sure that any input given through the console is the correct input
     * @param input the Scanner object that pulls user input from the console
     * @return a double of the values entered to the console
     */
    public static double validateBankInput(Scanner input){
        double temp;
        try{
            temp = Double.parseDouble(input.nextLine());
            if(temp < 0){
                System.out.println("Enter a non-negative number");
                return -1;
            }
            else{
                return temp;
            }
        } catch (Exception e){
            System.out.println("Enter a valid number");
            return -1;
        }
    }
}
