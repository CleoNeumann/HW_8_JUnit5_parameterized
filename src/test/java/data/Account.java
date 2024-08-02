package data;

public enum Account {
    GRANGER("Hermoine Granger", 1001, 5096, 4, "Dollar"),
    POTTER("Harry Potter", 1006, 0, 200, "Rupee"),
    WEASLY("Ron Weasly", 1008, 0, 100, "Pound");

    private final String customerName;
    private final int accountNumber;
    private final int currentAmount;
    private final int increaseAmount;
    private final String customerCurrency;


    Account(String customerName, int accountNumber, int currentAmount,
            int increaseAmount, String currency) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.currentAmount = currentAmount;
        this.increaseAmount = increaseAmount;
        this.customerCurrency = currency;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAccountNumber() {
        return String.valueOf(accountNumber);
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public String getIncreaseAmount() {
        return String.valueOf(increaseAmount);
    }

    public String getCurrency() {
        return customerCurrency;
    }

    public int getFinalAmount() {
        return currentAmount + increaseAmount;
    }
}
