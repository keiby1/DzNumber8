package com.company;

/**
 * Created by lushin on 03.02.2017.
 */
public class ExtraThread implements Runnable {
    private SynchronizedAccount account;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(account.getBalance());
            account.Increase(10);
            if (account.getBalance() < 0)
                System.out.println("Перерасход!");
        }
    }

    ExtraThread(SynchronizedAccount acc) {
        account = acc;
    }
}
