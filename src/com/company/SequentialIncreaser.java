package com.company;

import java.util.Random;

/**
 * Created by lushi on 22.12.2016.
 */
public class SequentialIncreaser implements Runnable {
    private Card card;

    @Override
    public void run() {
        synchronized (card) {
            Random random = new Random();
            int money = (random.nextInt(8) + 1) * 100;
            card.Increase(money);
            System.out.println("Увеличено на " + money + " Баланс: " + card.GetBalance());
        }
    }

    SequentialIncreaser(Card c) {
        card = c;
    }
}
