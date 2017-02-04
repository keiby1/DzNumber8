package com.company;

/**
 * Created by lushi on 28.11.2016.
 */
public class LackMoneyException extends Exception {
    public void Warning() {
        System.out.println("Не достаточно средств!");
    }

    public void Recommendation() {
        System.out.println("Проверьте баланс и попробуйте снова!");
    }
}
