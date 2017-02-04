package com.company;

/**
 * Created by lushi on 22.12.2016.
 */
public class Account {
    private int Balance = 50;

    public int getBalance() {
        return Balance;
    }

    public synchronized void putMoney(int money) {
        Balance += money;
    }

    public synchronized void getMoney(int money) {
        Balance = Balance - money;
    }
}
