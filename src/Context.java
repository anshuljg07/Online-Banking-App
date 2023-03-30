/**
 * The Context class is used to share global data between the controllers. Specifically the AccounHolder that the user selected
 * and then the Bank that is used to deal with the transactions
 */
public class Context {
    /**
     * There is a static Context context which allows us to access the context without instantiating a context class in
     * any of the controllers.
     */
    private static Context context = new Context();

    /**
     * The Bank bank is the bank that is used to handle transactions and the AccountHolders that are created through
     * the GUI
     */
    private final Bank bank = new Bank();

    /**
     * The AccountHolder selectedAccount is the AccountHolder that the user has selected to modify
     */
    private AccountHolder selectedAccount;

    /**
     * The static getter method for context allows us to get the Context without having to instantiate it
     * @return the context
     */
    public static Context getContext(){
        return context;
    }

    /**
     * The getter method for the bank instance variable
     * @return
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * The setter method for the selectedAccount
     * @param ah
     */
    public void setSelectedAccount(AccountHolder ah){
        selectedAccount = ah;
    }

    /**
     * The getter method for the selectedAccount
     * @return
     */
    public AccountHolder getSelectedAccount(){
        return selectedAccount;
    }
}
