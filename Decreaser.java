package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by lushi on 22.12.2016.
 */
class Decreaser extends Thread {
    private Card card;

    @Override
    public void run() {
        Random random = new Random();
        int money = (random.nextInt(8) + 1) * 100;
        card.Reduce(money);
        System.out.println("Снято: " + money + " Баланс: " + card.GetBalance());
    }

    Decreaser(Card c) {
        card = c;
    }
}