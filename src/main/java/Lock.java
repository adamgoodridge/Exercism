class BankBalance {
    private int balance;
    private Object balanceLock;
    public void open() {
        balance = 0;
        balanceLock = new Object();
    }

    public void deposit(int value) {
        synchronized (balanceLock) {
            balance += value;
        }
    }

    public void withdrawn(int value) throws BankBalanceActionInvalidException {
        synchronized (balanceLock) {
            if(balance == 0)
                throw new BankBalanceActionInvalidException("Cannot withdraw money from an empty account");
            if(value > balance)
                throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
            balance -= value;
        }
    }

    public int getBalance() {
        return balance;
    }
}