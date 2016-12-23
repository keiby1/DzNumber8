package com.company;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by lushi on 22.12.2016.
 */
class Increaser extends Thread {
    private Card card;

    @Override
    public void run() {
        Random random = new Random();
        int money = (random.nextInt(8) + 1) * 100;
        card.Increase(money);
        System.out.println("Увеличено на " + money + " Баланс: " + card.GetBalance());
    }

    Increaser(Card c) {
        card = c;
    }
}
