package com.company;

/**
 * Created by lushi on 28.11.2016.
 */
public class BlockingAccountException extends IncorrectPINException {
    public void Warning() {
        System.out.println("Произошла блокировка аккаунта!");
    }

    public void Recommendation() {
        System.out.println("Вы три раза ввели не верный PIN!");
    }

    public static void Ban(long Time) {
        /*
        Метод для временной блокировки аккаунта на некоторое время(передается параметром).
         */
        try {
            Thread.sleep(Time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
