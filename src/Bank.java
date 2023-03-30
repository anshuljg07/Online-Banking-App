import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Bank class is from the Exchangecmputation base code and defines the functionality of a bank and the associated
 * functions like withdraw, deposit, and more
 */
public class Bank {
    /**
     * The BigDecimal conversion rate is the conversion rate between USD and SWD
     */
    private static BigDecimal conversionRate = new BigDecimal(2);

    /**
     * The AccountHolderList accountHolderList contains the AccountHolders and the functinoality to add, get, and remove them
     */
    private final AccountHolderList accountHolderList= new AccountHolderList();

    /**
     * The Bank constructor with no parameters
     */
    public Bank(){
    }

    /**
     * The Bank constructor with parameter double cRate initializes the conversion rate
     * @param cRate this is the conversion rate between USD and SWD
     */
    public Bank(double cRate){
        setConversionRate(cRate);
    }

    /**
     * The setter method for the conversion rate between USD and sWD
     * @param rate conversion rate
     */
    public static void setConversionRate(double rate){
        if(rate <0){
            throw new IllegalArgumentException("Exchange Rates must be non-negative values");
        }
        else{
            conversionRate = new BigDecimal(rate);
        }
    }

    /**
     * The getter method for the conversion rate
     * @return the conversion rate
     */
    public static BigDecimal getConversionRate(){
        return conversionRate;
    }

    /**
     * The getter method for accountHolderList
     * @return the accountHolderList
     */
    public AccountHolderList getAccountHolderList() {
        return accountHolderList;
    }

    /**
     * The createNewAccount() method instantiates a new AccountHolder and then adds it to the accountHolderList
     * @param name the account holder name
     * @param initialBalance the initial balance
     * @return returns the gowda id
     */
    public int createNewAccount(String name, double initialBalance){
        AccountHolder ahToAdd = new AccountHolder(name, initialBalance);
        return accountHolderList.addAccountHolder(ahToAdd);
    }

    /**
     * The deposit method and modifies the SWD balance and the USD balance.
     * @param ah the account holder that will be modified
     * @param SWDDepositAmount the amount in SWD to be deposited
     */
    public void deposit(AccountHolder ah, double SWDDepositAmount){
        BigDecimal SWDDeposit = new BigDecimal(SWDDepositAmount);
        BigDecimal USDDepositAmount = SWDDeposit.divide(getConversionRate(), 4, RoundingMode.HALF_UP);
        BigDecimal userBalanceUSD = ah.getBalance().setScale(2, RoundingMode.HALF_UP);
        BigDecimal userBalanceSWD = ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP);

        System.out.println("Exchange rate is " + getConversionRate().setScale(2, RoundingMode.HALF_UP));
        ah.setBalance(userBalanceUSD.add(USDDepositAmount).doubleValue());
        ah.setSWDBalance(userBalanceSWD.add(SWDDeposit).doubleValue());
        System.out.println("Your new Balance is " + ah.getBalance().setScale(2, RoundingMode.HALF_UP) + " USD and " + ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP) + " SWD");
    }

    /**
     * The withdraw() method modifies the SWD and USD balance after subtracting the deposit amount and then
     * returns whether or not the withdraw was successful
     * @param ah the account holder that it will be drawn from
     * @param SWDWithdrawAmount the amount in SWD to take it from
     * @return whether or not the withdraw as successful
     */
    public boolean withdraw(AccountHolder ah, double SWDWithdrawAmount){
        BigDecimal SWDWithdraw = new BigDecimal(SWDWithdrawAmount);
        BigDecimal USDWithdrawAmount = SWDWithdraw.divide(getConversionRate(), 4, RoundingMode.HALF_UP);
        BigDecimal userBalanceUSD = ah.getBalance().setScale(2, RoundingMode.HALF_UP);
        BigDecimal userBalanceSWD = ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP);

        if(USDWithdrawAmount.compareTo(userBalanceUSD) > 0 || ah.getSWDBalance().doubleValue() <= 0.00999){
            System.out.println("Account Balance Insufficient for a withdrawal");
            return false;
        }

        System.out.println("Exchange rate is " + getConversionRate().setScale(2, RoundingMode.HALF_UP));
        ah.setBalance(userBalanceUSD.subtract(USDWithdrawAmount).doubleValue());
        ah.setSWDBalance(userBalanceSWD.subtract(SWDWithdraw).doubleValue());
        System.out.println("Your new balance is " + ah.getBalance().setScale(2, RoundingMode.HALF_UP) + " USD and " + ah.getSWDBalance().setScale(2, RoundingMode.HALF_UP) + " SWD");
        System.out.println("You receive: " + SWDWithdraw + " SWD");
        return true;
    }

    /**
     * The closeAccount() method closes the account provided and sets the account balances to zero
     * @param ah the account holder to close
     */
    public void closeAccount(AccountHolder ah){
//        printUSDBills(ah);
        accountHolderList.deleteAccountHolder(ah.getBankAccountHolderID());
        ah.setBalance(0);
        ah.setSWDBalance(ah.getBalance().doubleValue());
    }

    /**
     * The method printUSDBills() prints the amount of money in USD bills combination
     * @param ah the accountholder from which the balance is being pulled from
     * @return the string of usd bills
     */
    public String printUSDBills(AccountHolder ah){
        int twentyDollar = 0;
        int tenDollar = 0;
        int fiveDollar = 0;
        int oneDollar = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        int penny = 0;
        double money = ah.getBalance().doubleValue();
        String changeOutput = "USD Bill Conversion:\n";

        while(money >= -1){
            if(money >= 20){
                twentyDollar = (int) (money / 20.0);
                money -= twentyDollar * 20;
            }
            else if(money >= 10){
                tenDollar = (int) (money / 10.0);
                money -= tenDollar * 10;
            }
            else if(money >= 5){
                fiveDollar = (int) (money / 5.0);
                money -= fiveDollar * 5;
            }
            else if(money >= 1){
                oneDollar++;
                money -= 1;
            }
            else if(money >= 0.25){
                quarter++;
                money -= 0.25;
            }
            else if (money >= 0.10){
                dime++;
                money -= 0.10;
            }
            else if(money >= 0.05){
                nickel++;
                money -= 0.05;
            }
            else if(money >= 0.01){
                penny++;
                money -= 0.01;
            }
            else if(money >= 0.00999){
                penny++;
                money = -1;
            }
            else{
                changeOutput += twentyDollar + " Twenty Dollar Bills\n";
                changeOutput += tenDollar + " Ten Dollar Bills\n";
                changeOutput += fiveDollar + " Five Dollar Bills\n";
                changeOutput += oneDollar + " One Dollar Bills\n";
                changeOutput += quarter + " Twenty-Five Cent Coins\n";
                changeOutput += dime + " Ten Cent Coins\n";
                changeOutput += nickel + " Five Cent Coins\n";
                changeOutput += penny + " Once Cent Coins\n";
//                System.out.println("Account Details in USD");
//                System.out.println("Total USD: " + ah.getBalance().setScale(2,RoundingMode.FLOOR));
//                System.out.println(twentyDollar + " Twenty dollar bills");
//                System.out.println(tenDollar + " Ten dollar bills");
//                System.out.println(fiveDollar + " Five dollar bills");
//                System.out.println(oneDollar + " One dollar bills");
//                System.out.println(quarter + " Twenty-Five cent coins");
//                System.out.println(dime + " Ten cent coins");
//                System.out.println(nickel + " Five cent coins");
//                System.out.println(penny + " One cent coins");
                //sets persons balance to zero and reference path to null.
                return changeOutput;
            }
        }
        return changeOutput;
    }

    /**
     * The SWDBIllConversion returns the String rep of the balance provided in SWD Bill currency
     * @param amount the amount that will be truned into SWD currency
     * @return the string rep in SWD currency
     */
    public String SWDBillConversion(double amount){
        String changeOutput = "SWD Bills Conversion:\n";
        int twentyFiveDollar = 0;
        int tenDollar = 0;
        int fiveDollar = 0;
        int oneDollar = 0;
        int twentyCents = 0;
        int eightCents = 0;
        int fiveCents = 0;
        int oneCent = 0;

        double money = amount;

        while(money >= -1){
            if(money >= 25){
                twentyFiveDollar = (int) (money / 25.0);
//                twentyFiveDollar = multiples;
                money -= twentyFiveDollar * 25;
            }
            else if(money >= 10){
                tenDollar = (int) (money / 10.0);
//                tenDollar = multiples;
                money -= tenDollar * 10;
            }
            else if(money >= 5){
                fiveDollar = (int) (money / 5.0);
                money -= fiveDollar * 5;
            }
            else if(money >= 1){
                oneDollar++;
                money -= 1;
            }
            else if(money >= 0.20){
                twentyCents++;
                money -= 0.20;
            }
            else if(money >= 0.08){
                eightCents++;
                money -= 0.08;

            }
            else if(money >= 0.05){
                fiveCents++;
                money -= 0.05;

            } else if(money >= 0.01) {
                oneCent++;
                money -= 0.01;
            }
            else if(money >= 0.00999){
                oneCent++;
                money = -1;
            }
            else{
                changeOutput += twentyFiveDollar+ " Twenty-Five Dollar Bills\n";
                changeOutput += tenDollar + " Ten Dollar Bills\n";
                changeOutput += fiveDollar + " Five Dollar Bills\n";
                changeOutput += oneDollar + " One Dollar Bills\n";
                changeOutput += twentyCents + " Twenty Cent Coins\n";
                changeOutput += eightCents + " Eight Cent Coins\n";
                changeOutput += fiveCents + " Five Cent Coins\n";
                changeOutput += oneCent + " One Cent Coins\n";
                return changeOutput;
            }
        }
        return changeOutput;
    }
}
