package com.company;

/**
 * Created by lushi on 22.12.2016.
 */
public class NewThread implements Runnable {
    private SynchronizedAccount account = new SynchronizedAccount();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(account.getBalance());
            account.Decrease(10);
            if (account.getBalance() < 0)
                System.out.println("Перерасход!");
        }
    }

    NewThread(SynchronizedAccount acc) {
        account = acc;
    }
}
