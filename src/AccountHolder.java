import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The AccountHolder class is part of the exchange computation base code that defines an account holder that is part of the
 * bank. It comes with a name and balances in SWD and USD
 */
public class AccountHolder {

    /**
     * The bankAccountHolderID is the unique GOWDA ID associated with this account holder
     */
    private int bankAccountHolderID;
    /**
     * The String accountHolderNmae is the String name associated with this AccountHolder
     */
    private String accountHolderName;
    /**
     * The BigDecimal bankBalance is the balance in USD
     */
    private BigDecimal bankBalance;
    /**
     * The BigDecimal SWDBalnce is the balance in SWD
     */
    private BigDecimal SWDBalance;
//    private double counter = 0; //TODO: FROM OTHER SOURCE

    /**
     * The AccountHolder() constructor takes parameters String name double initialAmount and initializes the name, and both balances.
     * @param name the String name
     * @param initialAmount the initial balance in USD
     */
    public AccountHolder(String name, double initialAmount){
        accountHolderName = name;
        SWDBalance = new BigDecimal(0);
        setBalance(initialAmount);
    }

    /**
     * The AccountHolder() constructor with no parameters sets the SWD balance and the USD balance
     */
    public AccountHolder(){
        SWDBalance = new BigDecimal(0);
        setBalance(1);
    }

    //TODO: Double check the initialCreation condition to make sure the logic is similar ot source

    /**
     * The setBalance() method is the setter for balance in USD
     * @param balance the balance in USD
     */
    public void setBalance(double balance){
        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        bankBalance = new BigDecimal(balance);
        setSWDBalance(bankBalance.multiply(Bank.getConversionRate()).setScale(4, RoundingMode.HALF_UP).doubleValue());
//        setSWDBalance(getBalance().divide(Bank.getConversionRate(), 4, RoundingMode.HALF_UP));
//        if(initialCreation){
//            setSWDBalance(bankBalance);
//            initialCreation = false;
//        }
    }

    /**
     * The toString() method is an overriden method which provides the String repsentation of an AccountHolder object
     * @return the String representation
     */
    @Override
    public String toString(){
        return "GOWDA ID: " + getBankAccountHolderID() + "\nAccount Holder Name: " + getAccountHolderName();
    }

    /**
     * The getter method for the bankAccountHolderID
     * @return the Gowda ID
     */
    public int getBankAccountHolderID(){
        return bankAccountHolderID;
    }

    /**
     * The getter method for the bankAccountHolderID
     * @param ID the gowda id
     */
    public void setBankAccountHolderID(int ID){
        bankAccountHolderID = ID;
    }

    /**
     * The getter method for the accountHolderName
     * @return String account holder name
     */
    public String getAccountHolderName(){
        return accountHolderName;
    }

    /**
     * The getter method for the bankBalance in USD with decimal scale of 2
     * @return
     */
    public BigDecimal getBalance(){
        return bankBalance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * The getter method for SWDBalance with decimal scale of 2
     * @return
     */
    public BigDecimal getSWDBalance(){
        return SWDBalance.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * The setter method for SWDBalance with decimal scale of 2
     * @param balance
     */
    public void setSWDBalance(double balance){
        SWDBalance = new BigDecimal(balance);
//        if(SWDBalance.doubleValue() == 0){
//            SWDBalance = balance.multiply(Bank.getConversionRate());
//        }
//        else{
//            SWDBalance = balance;
//        }
    }
}
