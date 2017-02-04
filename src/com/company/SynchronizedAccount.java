package com.company;

/**
 * Created by lushi on 22.12.2016.
 */
public class SynchronizedAccount {
    private Account account;

    public SynchronizedAccount(Account acc) {
        account = acc;
    }

    public void Decrease(int money) {
        synchronized (account) {
            if (account.getBalance() >= money)
                account.getMoney(money);
        }
    }

    public void Increase(int money) {
        synchronized (account) {
            account.putMoney(money);
        }
    }

    public int getBalance() {
        return account.getBalance();
    }
}
