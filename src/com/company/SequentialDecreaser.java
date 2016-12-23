package com.company;

import java.util.Random;

/**
 * Created by lushi on 22.12.2016.
 */
public class SequentialDecreaser implements Runnable {
    Card card;

    @Override
    public void run() {
        Random random = new Random();
        int money = (random.nextInt(8) + 1) * 100;
        card.Reduce(money);
        System.out.println("Снято: " + money + " Баланс: " + card.GetBalance());
    }

    SequentialDecreaser(Card c) {
        card = c;
    }
}
