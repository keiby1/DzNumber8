package com.company;

/**
 * Created by lushi on 22.12.2016.
 */
public class SynchronizedAccount {
    private Account account = new Account();

    public void Decrease(int money) {
        if (account.getBalance() >= money)
            account.getMoney(money);
    }

    public int getBalance() {
        return account.getBalance();
    }
}
