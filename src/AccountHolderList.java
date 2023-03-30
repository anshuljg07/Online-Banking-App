import java.util.ArrayList;

/**
 * The AccountHolderList class acts as a DB of all of the AccountHolders assocaited with a specific bank while providing
 * the functionality to add and removed them from the list.
 */
public class AccountHolderList {
    /**
     * The ArrayList<AccountHolder> is the list of AccountHolder objects that make up the bank
     */
    private final ArrayList<AccountHolder> AccountHolderList;

    /**
     * The AccountHolderList() constructor with no parameters instantiates the AccountholderList arraylist intance var
     */
    public AccountHolderList(){
        AccountHolderList = new ArrayList<>();
    }

    /**
     * The getter method for the AccountHolderList
     * @return the arraylist of AccountHolder objects
     */
    public ArrayList<AccountHolder> getAccountHolderList(){
        return AccountHolderList;
    }

    /**
     * The addAccountHolder method adds an AccountHolder object to the array list and then adds its Gowda ID to the object
     * @param toBeAdded the AccountHolder object to be added
     * @return returns the gowda id
     */
    public int addAccountHolder(AccountHolder toBeAdded) {
        AccountHolderList.add(toBeAdded);
        toBeAdded.setBankAccountHolderID(AccountHolderList.indexOf(toBeAdded));
        return toBeAdded.getBankAccountHolderID();
    }

    /**
     * The getAccountHolder method finds and returns an AccountHolder object using its gowda id and throws an exception if
     * the account holder could not be found
     * @param accountID the gowda id of the account holder
     * @return the AccountHolder object
     * @throws Exception throws exception if it could not be found
     */
    public AccountHolder getAccountHolder(int accountID) throws Exception {
        try {
            return AccountHolderList.get(accountID);
        }
        catch (Exception e){
            throw new Exception("Account not Found");
        }
    }

    /**
     * The deleteAccountHolder method deletes the AccountHolder object from the list using its GowdaID.
     * @param customerID the gowda id
     */
    public void deleteAccountHolder(int customerID){
        //        toBeDeleted.setBalance(0);
//        toBeDeleted.setSWDBalance(toBeDeleted.getBalance().doubleValue());
        AccountHolderList.remove(customerID);
    }
}
